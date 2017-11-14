package ru.job4j.userstorage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса UserStore.
 */
public class UserStoreTest {

    private final UserStore users = UserStore.getInstance();

    /**
     * Изначальный сброс таблицы.
     */
    @Before
    public void clearTable() {
        users.clearTable();
    }

    /**
     * Тест добавления пользователя.
     */
    @Test
    public void whenAddUserThenReceiveUser() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis());
        users.addUser(user);

        assertThat(users.getUsers().get(0), is(user));
    }

    /**
     * Тест обновления пользователя.
     */
    @Test
    public void whenUpdateUserThenReceiveUpdatedUser() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis());
        users.addUser(user);

        User updated = new User("Update User", "Login", "newmail@example.com", user.getCreateDate());
        users.updateUser(updated);

        assertThat(users.getUsers().get(0), is(updated));
    }

    /**
     * Тест удаления пользователя.
     */
    @Test
    public void whenDeleteSingleUserThenUsersListIsEmpty() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis());
        users.addUser(user);

        users.deleteUser(user);

        assertThat(users.getUsers().isEmpty(), is(true));
    }

    /**
     * Тест поиска по логину.
     */
    @Test
    public void whenAddUserThenReceiveUserByLogin() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis());
        users.addUser(user);

        assertThat(users.findByLogin("Login"), is(user));
    }

    /**
     * Тест добавления нескольких пользователей.
     */
    @Test
    public void whenAddMultipleUsersThenGetMultipleUsersList() {
        User first = new User("First user", "First Login", "1user@example.com", System.currentTimeMillis());
        User second = new User("Second user", "Second Login", "2user@example.com", System.currentTimeMillis());
        User third = new User("Third user", "Third Login", "4user@example.com", System.currentTimeMillis());

        users.addUser(first);
        users.addUser(second);
        users.addUser(third);

        List<User> expected = new ArrayList<>(Arrays.asList(first, second, third));

        assertThat(users.getUsers(), is(expected));
    }


}
