package ru.job4j.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDAOTest {

    private DAOInterface<User> dao = new GenericDAO<>();
    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        dao.executeQuery("Truncate table users restart identity cascade");
    }
    /**
     * Test of adding new user.
     */
    @Test
    public void whenAddUserThenGetCorrectUser() {
        User user = new User();
        user.setLogin("test1");
        user.setPassword("pass");
        dao.saveOrUpdate(user);
        List<User> result = dao.getAll(User.class);
        assertThat(result.get(0), is(user));
    }
    /**
     * Test of updating user.
     */
    @Test
    public void whenUpdateUserThenGetUpdatedResult() {
        User user = new User();
        user.setLogin("test1");
        user.setPassword("pass");
        dao.saveOrUpdate(user);
        user.setLogin("newLogin");
        dao.saveOrUpdate(user);
        List<User> result = dao.getAll(User.class);
        assertThat(result.get(0), is(user));
    }
    /**
     * Test of deleting user.
     */
    @Test
    public void whenDeleteUserThenResultListIsEmpty() {
        User user = new User();
        user.setLogin("test1");
        user.setPassword("pass");
        dao.saveOrUpdate(user);
        dao.delete(user);
        List<User> result = dao.getAll(User.class);
        assertTrue(result.isEmpty());

    }
    /**
     * Test of getting users list.
     */
    @Test
    public void whenAddCoupleUsersThenGetAllUsers() {
        User user = new User();
        user.setLogin("test1");
        user.setPassword("pass");
        dao.saveOrUpdate(user);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("god");
        dao.saveOrUpdate(admin);

        List<User> expected = new ArrayList<>(Arrays.asList(user, admin));
        List<User> result = dao.getAll(User.class);

        assertThat(result, is(expected));
    }
    /**
     * Test of searching user by ID.
     */
    @Test
    public void whenSearchUserByIDThenGetUser() {
        User expected = new User();
        expected.setLogin("test1");
        expected.setPassword("pass");
        dao.saveOrUpdate(expected);

        User result = dao.findById(User.class, expected.getId());

        assertThat(result, is(expected));
    }
}