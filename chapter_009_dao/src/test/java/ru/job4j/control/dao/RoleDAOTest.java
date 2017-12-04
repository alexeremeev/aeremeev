package ru.job4j.control.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.models.Role;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тесты RoleDAO.
 */
public class RoleDAOTest {

    private final PSQLpool pool = PSQLpool.getInstance();
    private final RoleDAO dao = new RoleDAO(pool);

    /**
     * Очитска таблицы.
     */
    @Before
    public void clearTable() {
        dao.clearTable();
    }

    /**
     * Тест добавления роли.
     */
    @Test
    public void whenAddRoleThenGetRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("user");
        dao.createRole(role);
        assertThat(dao.getAll().get(0), is(role));
    }

    /**
     * Тест редактирования роли.
     */
    @Test
    public void whenUpdateRoleThenGetUpdatedRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("user");

        dao.createRole(role);

        role.setName("admin");

        dao.updateRole(role);

        assertThat(dao.getAll().get(0), is(role));
    }

    /**
     * Тест поиска роли по ID.
     */
    @Test
    public void whenSearchRoleByIDThenGetRole() {
        Role expected = new Role();
        expected.setId(1);
        expected.setName("user");

        dao.createRole(expected);

        assertThat(dao.findRoleById(1), is(expected));
    }

    /**
     * Тест поиска роли по названию.
     */
    @Test
    public void whenSearchRoleByNameThenGetRole() {
        Role expected = new Role();
        expected.setId(1);
        expected.setName("user");

        dao.createRole(expected);

        assertThat(dao.findRoleByName("user"), is(expected));
    }

    /**
     * Тест удаления роли.
     */
    @Test
    public void whenDeleteRolesThenListIsEmpty() {
        Role role = new Role();
        role.setId(1);
        role.setName("user");

        dao.createRole(role);

        dao.deleteRole(1);

        assertThat(dao.getAll().isEmpty(), is(true));
    }

}