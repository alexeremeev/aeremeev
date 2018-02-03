package ru.job4j.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.service.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract DAO.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 * @param <T> model.
 */
public abstract class AbstractDAO<T> implements DAOInterface<T> {
    /**
     * Save new or update existing model.
     * @param model model.
     */
    @Override
    public void saveOrUpdate(T model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(model);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /**
     * Delete model.
     * @param model model.
     */
    @Override
    public void delete(T model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(model);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /**
     * Find model by ID.
     * @param type model class type.
     * @param id ID.
     * @return model.
     */
    @Override
    public T findById(Class<T> type, int id) {
        Session session = null;
        Transaction transaction = null;
        T model = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            model = session.load(type, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return model;
    }
    /**
     * Get list of all models.
     * @param type model class type.
     * @return list of all models.
     */
    @Override
    public List<T> getAll(Class<T> type) {
        Session session = null;
        Transaction transaction = null;
        List<T> result = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            Root<T> root = criteria.from(type);
            criteria.select(root);
            Query<T> query =session.createQuery(criteria);
            result = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    /**
     * Clear table.
     * @param query table truncate query.
     */
    @Override
    public void clearTable(String query) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
