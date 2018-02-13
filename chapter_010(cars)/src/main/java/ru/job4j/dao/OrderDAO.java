package ru.job4j.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.models.Order;
import ru.job4j.service.HibernateUtil;

import java.util.List;
import java.util.Map;

public class OrderDAO extends GenericDAO<Order> {

    private final TransactionManager manager = new TransactionManager(HibernateUtil.getSessionFactory());

    public List<Order> getOrdersByFilter(String hql, Map<String, Object> parameters) {
        if (parameters.size() == 0) {
            return super.getAll(Order.class);
        } else {
            return manager.doInTransaction(new TransactionCallable<List<Order>>() {
                @Override
                public List<Order> execute(Session session) {
                    Query query = session.createQuery(hql);
                    for (String param: parameters.keySet()) {
                        query.setParameter(param, parameters.get(param));
                    }
                    return query.list();
                }
            });
        }
    }
}
