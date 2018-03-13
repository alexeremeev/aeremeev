package ru.job4j.hiberwrapper.dao;

import java.util.List;

public interface DAOInterface<T> {

    void saveOrUpdate(T model);

    void delete(T model);

    T findById(Class<T> type, int id);

    List<T> getAll(Class<T> type);

    void executeQuery(String query);
}
