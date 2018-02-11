package ru.job4j.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.service.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Generic DAO.
 * @author aeremeev.
 * @version 1.1
 * @since 01.02.2018
 * @param <T> model.
 */
public class GenericDAO<T> implements DAOInterface<T> {
    /**
     * Transaction manager.
     */
    private final TransactionManager manager = new TransactionManager(HibernateUtil.getSessionFactory());
    /**
     * Save new or update existing model.
     * @param model model.
     */
    @Override
    public void saveOrUpdate(T model) {
        manager.doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                session.saveOrUpdate(model);
                return null;
            }
        });
    }
    /**
     * Delete model.
     * @param model model.
     */
    @Override
    public void delete(T model) {
        manager.doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                session.delete(model);
                return null;
            }
        });
    }
    /**
     * Find model by ID.
     * @param type model class type.
     * @param id ID.
     * @return model.
     */
    @Override
    public T findById(Class<T> type, int id) {
        return manager.doInTransaction(new TransactionCallable<T>() {
            @Override
            public T execute(Session session) {
                return session.load(type, id);
            }
        });
    }
    /**
     * Get list of all models.
     * @param type model class type.
     * @return list of all models.
     */
    @Override
    public List<T> getAll(Class<T> type) {
        return manager.doInTransaction(new TransactionCallable<List<T>>() {
            @Override
            public List<T> execute(Session session) {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<T> criteria = builder.createQuery(type);
                Root<T> root = criteria.from(type);
                criteria.select(root);
                Query<T> query = session.createQuery(criteria);
                return query.getResultList();
            }
        });
    }
    /**
     * Clear table.
     * @param query table truncate query.
     */
    @Override
    public void executeQuery(String query) {
        manager.doInTransaction(new TransactionCallable<Void>() {
            @Override
            public Void execute(Session session) {
                session.createNativeQuery(query).executeUpdate();
                return null;
            }
        });
    }
}
