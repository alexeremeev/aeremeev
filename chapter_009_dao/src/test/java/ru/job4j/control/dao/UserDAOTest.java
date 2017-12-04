package ru.job4j.control.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты UserDAO.
 */
public class UserDAOTest {

    private final PSQLpool pool = PSQLpool.getInstance();
    private final UserDAO userDAO = new UserDAO(pool);

    /**
     * Очитска таблицы.
     */
    @Before
    public void clearTable() {
        userDAO.clearTable();
    }

    /**
     * Тест добавления пользовтеля.
     */
    @Test
    public void whenAddUserThenGetUser() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        user.setPassword("user");
        user.setRoleId(1);
        user.setAddressId(1);
        user.setMusicTypeId(1);
        userDAO.createUser(user);
        assertThat(userDAO.getAll().get(0), is(user));
    }

    /**
     * Тест редактирования пользователя.
     */
    @Test
    public void whenUpdatedUserThenGetUpdatedUser() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        user.setPassword("user");
        user.setRoleId(1);
        user.setAddressId(1);
        user.setMusicTypeId(1);
        userDAO.createUser(user);

        user.setName("updated");
        user.setPassword("update password");

        userDAO.updateUser(user);

        assertThat(userDAO.getAll().get(0), is(user));
    }

    /**
     * Тест поиска пользователя по ID.
     */
    @Test
    public void whenSearchUserByIDThenGetUser() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        user.setPassword("user");
        user.setRoleId(1);
        user.setAddressId(1);
        user.setMusicTypeId(1);
        userDAO.createUser(user);

        assertThat(userDAO.findUserById(1), is(user));
    }

    /**
     * Тест поиска пользователя по имени.
     */
    @Test
    public void whenSearchUserByNameThenGetUser() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        user.setPassword("user");
        user.setRoleId(1);
        user.setAddressId(1);
        user.setMusicTypeId(1);
        userDAO.createUser(user);

        assertThat(userDAO.findUserByName("user"), is(user));
    }

    /**
     * Тест удаления пользователя.
     */
    @Test
    public void whenDeleteUsersThenListIsEmpty() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        user.setPassword("user");
        user.setRoleId(1);
        user.setAddressId(1);
        user.setMusicTypeId(1);
        userDAO.createUser(user);

        userDAO.deleteUser(1);

        assertThat(userDAO.getAll().isEmpty(), is(true));
    }
}