package ru.job4j.control.dao;

import org.apache.log4j.Logger;
import ru.job4j.control.models.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * сlass AddressDAO - реализация DAO для модели Address.
 */
public class AddressDAO {

    private final static Logger LOGGER = Logger.getLogger(AddressDAO.class);
    /**
     * Пул коннетов к БД.
     */
    private PSQLpool pool;

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public AddressDAO(PSQLpool pool) {
        this.pool = pool;
    }

    /**
     * Создать адрес.
     * @param address адрес.
     * @return ID созданной строки в БД.
     */
    public int createAddress(Address address) {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO ADDRESS (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
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
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Удалить адрес.
     * @param id ID адреса.
     * @return true, если удален.
     */
    public boolean deleteAddress(int id) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("DELETE FROM ADDRESS WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
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
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Обновить адрес.
     * @param address адрес.
     * @return true, если обновлен.
     */
    public boolean updateAddress(Address address) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("UPDATE address SET name = ? WHERE id = ?");
            ps.setString(1, address.getName());
            ps.setInt(2, address.getId());
            ps.executeUpdate();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
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
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Получить список всех адресов.
     * @return список всех адресов.
     */
    public List<Address> getAll() {

        Connection connection = null;
        List<Address> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ADDRESS");
            rs = ps.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt(1));
                address.setName(rs.getString(2));
                result.add(address);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Найти адрес по ID.
     * @param id ID адреса.
     * @return адрес, если найден, иначе null.
     */
    public Address findAddressById(int id) {
        Address result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ADDRESS WHERE ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new Address();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }
    /**
     * Найти адрес по названию.
     * @param name название адреса.
     * @return адрес, если найден, иначе null.
     */
    public Address findAddressByName(String name) {
        Address result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ADDRESS WHERE NAME = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new Address();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Сбросить таблицу.
     */
    public void clearTable() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("TRUNCATE TABLE ADDRESS RESTART IDENTITY CASCADE");
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
