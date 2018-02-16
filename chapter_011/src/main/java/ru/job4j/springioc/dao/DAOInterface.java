package ru.job4j.springioc.dao;

import java.util.List;

/**
 * DAO interface.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 * @param <T> model.
 */
public interface DAOInterface<T> {

    /**
     * Save new or update existing model.
     * @param model model.
     */
    void saveOrUpdate(T model);
    /**
     * Delete model.
     * @param model model.
     */
    void delete(T model);

    /**
     * Find model by ID.
     * @param type model class type.
     * @param id ID.
     * @return model.
     */
    T findById(Class<T> type, int id);

    /**
     * Get list of all models.
     * @param type model class type.
     * @return list of all models.
     */
    List<T> getAll(Class<T> type);

    /**
     * Clear table.
     * @param query table truncate query.
     */
    void executeQuery(String query);
}
