package ru.job4j.hiberwrapper;

import org.hibernate.Session;

@FunctionalInterface
public interface TransactionCallable<T> {
    T execute(Session session);
}
