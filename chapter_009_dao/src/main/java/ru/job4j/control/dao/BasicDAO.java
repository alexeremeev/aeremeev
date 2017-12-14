package ru.job4j.control.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * abstract class BasicDAO - базовая имплементация DAO.
 * @param <T> модель.
 */
public abstract class BasicDAO<T> implements InterfaceDAO {

    /**
     * Пул коннетов к БД.
     */
    protected PSQLpool pool;

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public BasicDAO(PSQLpool pool) {
        this.pool = pool;
    }

    /**
     * Получает из ResultSet List<T> моделей.
     * @param rs ResultSet.
     * @return List<T> моделей.
     * @throws SQLException SQLException.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    /**
     * Создать запись в БД.
     * @param fields поля для PreparedStatement.
     * @param query строка запроса.
     * @return ID созданной строки в БД.
     */
    public int create(Object[] fields, String query) {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt("id");
            }
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            result = 0;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    /**
     * Удалить запись из БД.
     * @param id ID записи.
     * @param query строка запроса.
     * @return true, если удалена.
     */
    public boolean delete(int id, String query) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            result = false;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Обновить запись.
     * @param fields поля для PreparedStatement.
     * @param query строка запроса.
     * @return true, если обновлена.
     */
    public boolean update(Object[] fields, String query) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                ps.setObject(i + 1, fields[i]);
            }
            ps.executeUpdate();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            result = false;
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    /**
     * Получить список всех записей в БД.
     * @param fields поля для PreparedStatement.
     * @param query строка запроса.
     * @return список всех записей <T> в БД.
     */
    public List<T> getAll(Object[] fields, String query) {
        Connection connection = null;
        List<T> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setObject(1, fields[0]);
            rs = ps.executeQuery();
            result = parseResultSet(rs);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Очистить таблицу.
     * @param query строка запроса.
     */
    public void clearTable(String query) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement(query);
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Заполнить таблицу значениями.
     * @param names массив значений.
     * @param query строка запроса.
     */
    public void fill(String[] names, String query) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(query);
            for (String name : names) {
                ps.setString(1, name);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
