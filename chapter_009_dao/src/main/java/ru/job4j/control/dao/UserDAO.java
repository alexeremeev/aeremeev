package ru.job4j.control.dao;

import org.apache.log4j.Logger;
import ru.job4j.control.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * сlass UserDAO - реализация DAO для модели User.
 */

public class UserDAO {

    private final static Logger LOGGER = Logger.getLogger(UserDAO.class);
    /**
     * Пул коннетов к БД.
     */
    private PSQLpool pool;

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public UserDAO(PSQLpool pool) {
        this.pool = pool;
    }

    /**
     * Создать пользователя.
     * @param user пользовател.
     * @return ID созданной строки в БД.
     */
    public int createUser(User user) {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO USERS (NAME, PASSWORD, ROLE_ID, ADDRESS_ID) VALUES (?, ?, ? ,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setInt(4, user.getAddressId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
                user.setId(result);
                this.setMusicType(user);
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
     * Удалить пользователя.
     * @param id ID пользователя.
     * @return true, если удален.
     */
    public boolean deleteUser(int id) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("DELETE FROM USERS WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            result = true;
            ps = connection.prepareStatement("DELETE FROM USER_MUSICTYPES WHERE user_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
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
     * Обновить пользователя.
     * @param user пользователь.
     * @return true, если обновлен.
     */
    public boolean updateUser(User user) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("UPDATE USERS SET NAME = ?, PASSWORD = ?, ROLE_ID = ?, ADDRESS_ID = ? WHERE ID = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setInt(4, user.getAddressId());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
            this.setMusicType(user);
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
     * Получить список всех пользователей.
     * @return список всех пользователей.
     */
    public List<User> getAll() {
        Connection connection = null;
        List<User> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USERS");
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRoleId(rs.getInt(4));
                user.setAddressId(rs.getInt(5));
                this.fillMusicTypes(user);
                result.add(user);
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
     * Найти пользователя по ID.
     * @param id ID пользователя.
     * @return пользователь, если найден, иначе null.
     */
    public User findUserById(int id) {
        User result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new User();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
                result.setPassword(rs.getString(3));
                result.setRoleId(rs.getInt(4));
                result.setAddressId(rs.getInt(5));
                this.fillMusicTypes(result);
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
     * Найти пользователя по имени.
     * @param name имя пользователя.
     * @return пользователь, если найден, иначе null.
     */
    public User findUserByName(String name) {
        User result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USERS WHERE NAME = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new User();
                result.setId(rs.getInt(1));
                result.setName(rs.getString(2));
                result.setPassword(rs.getString(3));
                result.setRoleId(rs.getInt(4));
                result.setAddressId(rs.getInt(5));
                this.fillMusicTypes(result);
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
     * Запонить множество музыкальных стилей пользователя.
     * @param user пользователь.
     * @return true, если заполнен.
     */
    private boolean fillMusicTypes(User user) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USER_MUSICTYPES WHERE USER_ID = ?");
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setMusicTypeId(rs.getInt(2));
            }
            result = true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
            result = false;
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
     * Установить музыкальные стили пользователя в БД.
     * @param user пользователь.
     * @return true, если установлены.
     */
    private boolean setMusicType(User user) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            Set<Integer> userMusicTypes = user.getMusicTypeId();
            ps = connection.prepareStatement("DELETE FROM USER_MUSICTYPES WHERE USER_ID = ?");
            ps.setInt(1, user.getId());
            ps.executeUpdate();
           connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO USER_MUSICTYPES (musictype_id, user_id) VALUES (?, ?)");
            for (int number : userMusicTypes) {
                ps.setInt(1, number);
                ps.setInt(2, user.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            result = true;
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            LOGGER.error(sqle.getMessage(), sqle);
            result = false;
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
     * Проверка связки имя-пароль пользователя.
     * @param name имя.
     * @param password пароль.
     * @return пользователь, если соответствуют, иначе null.
     */
    public User isCredential(String name, String password) {
        User exists = null;
        User find = this.findUserByName(name);
        if (find != null && find.getName().equals(name) && find.getPassword().equals(password)) {
            exists = find;
        }
        return exists;
    }

    /**
     * Очистить таблицу в БД.
     */
    public void clearTable() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("TRUNCATE TABLE USERS RESTART IDENTITY CASCADE");
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
