package ru.job4j.todo.database;

import org.hibernate.Session;

/**
 * TransactionCallable - callable in transaction session's method action.
 * @author aeremeev.
 * @version 1
 * @since 08.02.2018
 * @param <T> generic type.
 */
public abstract class TransactionCallable<T> {
    /**
     * Executes hibernate session methods.
     * @param session hibernate session.
     * @return <T> value of returnable param type.
     */
    public abstract T execute(Session session);
}
