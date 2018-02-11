package ru.job4j.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * TransactionManager - manages session / transaction life cycle.
 * @author aeremeev.
 * @version 1
 * @since 08.02.2018
 */
public class TransactionManager {
    /**
     * Hibernate SessionFactory
     */
    private SessionFactory sessionFactory;
    /**
     * Default constructor.
     * @param sessionFactory hibernate SessionFactory.
     */
    public TransactionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Manages transaction life cycle.
     * @param callable TransactionCallable - callable in transaction session's method action.
     * @param <T> generic type of returnable value.
     * @return result of method, executed in session.
     */
    public <T> T doInTransaction(TransactionCallable<T> callable) {
        T result = null;
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = callable.execute(session);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
