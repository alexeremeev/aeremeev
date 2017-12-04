package ru.job4j.control.dao;

import org.apache.log4j.Logger;
import ru.job4j.control.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * сlass RoleDAO - реализация DAO для модели Role.
 */
public class RoleDAO {
    private final static Logger LOGGER = Logger.getLogger(RoleDAO.class);

    /**
     * Пул коннетов к БД.
     */
    private PSQLpool pool;

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public RoleDAO(PSQLpool pool) {
        this.pool = pool;
    }

    /**
     * Создать роль.
     * @param role роль.
     * @return ID созданной строки в БД.
     */
    public int createRole(Role role) {
        int result = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO ROLES (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role.getName());
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
     * Удалить роль.
     * @param id ID роли.
     * @return true, если удалена.
     */
    public boolean deleteRole(int id) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("DELETE FROM ROLES WHERE ID = ?");
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
     * Обновить роль.
     * @param role роль.
     * @return true, если обновлена.
     */
    public boolean updateRole(Role role) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("UPDATE ROLES SET name = ? WHERE id = ?");
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());
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
     * Получить список всех ролей.
     * @return список всех ролей.
     */
    public List<Role> getAll() {
        Connection connection = null;
        List<Role> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ROLES");
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
                result.add(role);
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
     * Найти роль по ID.
     * @param id ID роли.
     * @return роль, если найдена, иначе null.
     */
    public Role findRoleById(int id) {
        Role result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ROLES WHERE ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new Role();
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
     * Найти роль по названию.
     * @param name название роли.
     * @return роль, если найдена, иначе null.
     */
    public Role findRoleByName(String name) {
        Role result = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("SELECT * FROM ROLES WHERE NAME = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new Role();
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
     * Запонить таблицу предустановленными значениями.
     */
    public void fillRoles() {
        String[] types = new String[] {"admin", "mandator", "user"};
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = this.pool.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("INSERT INTO ROLES (NAME) VALUES (?)");
            for (String type : types) {
                ps.setString(1, type);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            LOGGER.error(sqle.getMessage(), sqle);
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
    }

    /**
     * Очистить таблицу.
     */
    public void clearTable() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.pool.getConnection();
            ps = connection.prepareStatement("TRUNCATE TABLE ROLES RESTART IDENTITY CASCADE");
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
