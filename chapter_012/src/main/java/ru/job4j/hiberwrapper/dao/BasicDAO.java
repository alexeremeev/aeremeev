package ru.job4j.hiberwrapper.dao;

import ru.job4j.hiberwrapper.SessionWrapper;
import ru.job4j.hiberwrapper.service.HibernateUtil;

import java.util.List;

public class BasicDAO<T> implements DAOInterface<T> {

    private final SessionWrapper wrapper = new SessionWrapper(HibernateUtil.getSessionFactory());

    @Override
    public void saveOrUpdate(T model) {
        wrapper.doInTransaction(session -> {
            session.saveOrUpdate(model);
            return null;
        });
    }

    @Override
    public void delete(T model) {
        wrapper.doInTransaction(session -> {
            session.delete(model);
            return null;
        });
    }

    @Override
    public T findById(Class<T> type, int id) {
        return wrapper.doInTransaction(session -> (session.find(type, id)));
    }

    @Override
    public List<T> getAll(Class<T> type) {
        return wrapper.doInTransaction(session -> (session.createQuery(String.format("FROM %s", type.getName())).list()));
    }

    @Override
    public void executeQuery(String query) {
        wrapper.doInTransaction(session -> (session.createNativeQuery(query).executeUpdate()));
    }
}
