package ru.job4j.hiberwrapper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionWrapper {

    private SessionFactory sessionFactory;

    public SessionWrapper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T doInTransaction(TransactionCallable<T> actions) {
        T result = null;
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = actions.execute(session);
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
