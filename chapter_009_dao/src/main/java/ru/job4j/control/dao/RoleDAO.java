package ru.job4j.control.dao;

import ru.job4j.control.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * сlass RoleDAO - реализация DAO для модели Role.
 */
public class RoleDAO extends BasicDAO {

    /**
     * Конструктор.
     * @param pool пул коннектов к БД.
     */
    public RoleDAO(PSQLpool pool) {
        super(pool);
    }

    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws SQLException {
        List<Role> result = new ArrayList<>();
        while (rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            result.add(role);
        }
        return result;
    }

    /**
     * Создать роль.
     * @param role роль.
     * @return ID созданной строки в БД.
     */
    public int createRole(Role role) {
        String query = "INSERT INTO ROLES (NAME) VALUES (?)";
        Object[] fields = new Object[] {role.getName()};
        return super.create(fields, query);
    }

    /**
     * Удалить роль.
     * @param id ID роли.
     * @return true, если удалена.
     */
    public boolean deleteRole(int id) {
        String query = "DELETE FROM ROLES WHERE ID = ?";
        return super.delete(id, query);
    }

    /**
     * Обновить роль.
     * @param role роль.
     * @return true, если обновлена.
     */
    public boolean updateRole(Role role) {
        String query = "UPDATE ROLES SET name = ? WHERE id = ?";
        Object[] fields = new Object[] {role.getName(), role.getId()};
        return super.update(fields, query);
    }

    /**
     * Получить список всех ролей.
     * @return список всех ролей.
     */
    public List<Role> getAll() {
        String query = "SELECT * FROM ROLES WHERE ID <> ?";
        Object[] fields = new Object[] {0};
        return super.getAll(fields, query);
    }

    /**
     * Найти роль по ID.
     * @param id ID роли.
     * @return роль, если найдена, иначе null.
     */
    public Role findRoleById(int id) {
        String query = "SELECT * FROM ROLES WHERE ID = ?";
        Object[] fields = new Object[] {id};
        List<Role> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Найти роль по названию.
     * @param name название роли.
     * @return роль, если найдена, иначе null.
     */
    public Role findRoleByName(String name) {
        String query = "SELECT * FROM ROLES WHERE NAME = ?";
        Object[] fields = new Object[] {name};
        List<Role> result = super.getAll(fields, query);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Запонить таблицу предустановленными значениями.
     */
    public void fillRoles() {
        String[] types = new String[] {"admin", "mandator", "user"};
        String query = "INSERT INTO ROLES (NAME) VALUES (?)";
        super.fill(types, query);
    }

    /**
     * Очистить таблицу.
     */
    public void clearTable() {
        String query = "TRUNCATE TABLE ROLES RESTART IDENTITY CASCADE";
        super.clearTable(query);
    }
}
