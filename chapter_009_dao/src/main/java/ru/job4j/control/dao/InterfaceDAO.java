package ru.job4j.control.dao;


import java.util.List;

/**
 * Интерфейс DAO CRUD.
 * @param <T> модель.
 */
public interface InterfaceDAO<T> {
    /**
     * Создать запись в БД.
     * @param fields поля для PreparedStatement.
     * @param query строка запроса.
     * @return ID созданной строки в БД.
     */
    int create(Object[] fields, String query);

    /**
     * Удалить запись из БД.
     * @param id ID записи.
     * @param query строка запроса.
     * @return true, если удалена.
     */
    boolean delete(int id, String query);

    /**
     * Обновить запись.
     * @param fields поля для PreparedStatement.
     * @param query строка запроса.
     * @return true, если обновлена.
     */
    boolean update(Object[] fields, String query);

    /**
     * Получить список всех записей в БД.
     * @param fields поля для PreparedStatement.
     * @param query строка запроса.
     * @return список всех записей <T> в БД.
     */
    List<T> getAll(Object[] fields, String query);

    /**
     * Очистить таблицу.
     * @param query строка запроса.
     */
    void clearTable(String query);

    /**
     * Заполнить таблицу значениями.
     * @param names массив значений.
     * @param query строка запроса.
     */
    void fill(String[] names, String query);
}
