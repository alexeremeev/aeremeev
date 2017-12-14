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

public class UserDAO extends BasicDAO {
    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public UserDAO(PSQLpool pool) {
        super(pool);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setRoleId(rs.getInt("role_id"));
            user.setAddressId(rs.getInt("address_id"));
            this.fillMusicTypes(user);
            result.add(user);
        }
        return result;
    }

    /**
     * Создать пользователя.
     * @param user пользовател.
     * @return ID созданной строки в БД.
     */
    public int createUser(User user) {
        String query = "INSERT INTO USERS (NAME, PASSWORD, ROLE_ID, ADDRESS_ID) VALUES (?, ?, ? ,?)";
        Object[] fields = new Object[] {user.getName(), user.getPassword(), user.getRoleId(), user.getAddressId()};
        int result = super.create(fields, query);
        if (result != 0) {
            user.setId(result);
            this.setMusicType(user);
        }
        return result;
    }

    /**
     * Удалить пользователя.
     * @param id ID пользователя.
     * @return true, если удален.
     */
    public boolean deleteUser(int id) {
        String query = "DELETE FROM USERS WHERE ID = ?";
        boolean result = false;
        if (super.delete(id, query)) {
            query = "DELETE FROM USER_MUSICTYPES WHERE user_id = ?";
            result = super.delete(id, query);
        }
        return result;
    }

    /**
     * Обновить пользователя.
     * @param user пользователь.
     * @return true, если обновлен.
     */
    public boolean updateUser(User user) {
        String query = "UPDATE USERS SET NAME = ?, PASSWORD = ?, ROLE_ID = ?, ADDRESS_ID = ? WHERE ID = ?";
        Object[] fields = new Object[] {user.getName(), user.getPassword(), user.getRoleId(), user.getAddressId(), user.getId()};
        boolean result = super.update(fields, query);
        if (result) {
            this.setMusicType(user);
        }
        return result;
    }

    /**
     * Получить список всех пользователей.
     * @return список всех пользователей.
     */
    public List<User> getAll() {
        String query = "SELECT * FROM USERS WHERE ID <> ?";
        Object[] fields = new Object[] {0};
        return super.getAll(fields, query);
    }

    /**
     * Найти пользователя по ID.
     * @param id ID пользователя.
     * @return пользователь, если найден, иначе null.
     */
    public User findUserById(int id) {
        String query = "SELECT * FROM USERS WHERE ID = ?";
        Object[] fields = new Object[] {id};
        List<User> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Найти пользователя по имени.
     * @param name имя пользователя.
     * @return пользователь, если найден, иначе null.
     */
    public User findUserByName(String name) {
        String query = "SELECT * FROM USERS WHERE NAME = ?";
        Object[] fields = new Object[] {name};
        List<User> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
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
            connection = super.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM USER_MUSICTYPES WHERE USER_ID = ?");
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setMusicTypeId(rs.getInt("musictype_id"));
            }
            result = true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            result = false;
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
            connection = super.pool.getConnection();
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
            result = false;
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
        String query = "TRUNCATE TABLE USERS RESTART IDENTITY CASCADE";
        super.clearTable(query);
    }
}
