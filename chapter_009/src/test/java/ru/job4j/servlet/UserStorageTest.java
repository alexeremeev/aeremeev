package ru.job4j.servlet;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.servlet.model.Address;
import ru.job4j.servlet.model.Role;
import ru.job4j.servlet.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса UserStore.
 */
public class UserStorageTest {

    private final UserStorage users = UserStorage.getInstance();

    /**
     * Изначальный сброс таблицы.
     */
    @Before
    public void clearTable() {
        users.clearTable();
        users.checkTable();
    }

    /**
     * Тест добавления пользователя.
     */
    @Test
    public void whenAddUserThenReceiveUser() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis(), "user");
        Role role = new Role("user");
        Address address = new Address();
        address.setCityId(1);
        user.setAddress(address);
        role.setId(2);
        role.setAdmin(false);
        user.setRole(role);
        users.addUser(user);
        assertThat(users.getUsers().get(1), is(user));
    }

    /**
     * Тест обновления пользователя.
     */
    @Test
    public void whenUpdateUserThenReceiveUpdatedUser() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis(), "user");
        Role role = new Role("user");
        role.setId(2);
        role.setAdmin(false);
        user.setRole(role);
        Address address = new Address();
        address.setCityId(1);
        user.setAddress(address);
        users.addUser(user);

        User updated = new User("Update User", "Login", "newmail@example.com", user.getCreateDate(), "user");
        updated.setRole(role);
        Address updAddres = new Address();
        updAddres.setCityId(12);
        updated.setAddress(updAddres);
        users.updateUser(updated);

        assertThat(users.getUsers().get(1), is(updated));
    }

    /**
     * Тест удаления пользователя.
     */
    @Test
    public void whenDeleteSingleUserThenUsersListIsEmpty() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis(), "user");
        Role role = new Role("user");
        role.setId(2);
        role.setAdmin(false);
        user.setRole(role);
        Address address = new Address();
        address.setCityId(1);
        user.setAddress(address);
        users.addUser(user);

        users.deleteUser(user);

        assertThat(users.getUsers().contains(user), is(false));

    }

    /**
     * Тест поиска по логину.
     */
    @Test
    public void whenAddUserThenReceiveUserByLogin() {
        User user = new User("User", "Login", "user@example.com", System.currentTimeMillis(), "user");
        Role role = new Role("user");
        role.setId(2);
        role.setAdmin(false);
        user.setRole(role);
        Address address = new Address();
        address.setCityId(1);
        user.setAddress(address);
        users.addUser(user);

        assertThat(users.findByLogin("Login"), is(user));
    }

    /**
     * Тест добавления нескольких пользователей.
     */
    @Test
    public void whenAddMultipleUsersThenGetMultipleUsersList() {
        User first = new User("First user", "First Login", "1user@example.com", System.currentTimeMillis(), "user");
        User second = new User("Second user", "Second Login", "2user@example.com", System.currentTimeMillis(), "user");
        User third = new User("Third user", "Third Login", "4user@example.com", System.currentTimeMillis(), "user");

        Role role = new Role("user");
        role.setId(2);
        role.setAdmin(false);
        Address address = new Address();
        address.setCityId(1);

        first.setRole(role);
        second.setRole(role);
        third.setRole(role);

        first.setAddress(address);
        second.setAddress(address);
        third.setAddress(address);


        users.addUser(first);
        users.addUser(second);
        users.addUser(third);

        List<User> expected = new ArrayList<>(Arrays.asList(first, second, third));

        assertThat(users.getUsers().subList(1, 4), is(expected));
    }

    /**
     * Тест получения изначальных ролей пользователя.
     */
    @Test
    public void getInitialRoles() {
        Role admin = new Role("admin");
        admin.setId(1);
        admin.setAdmin(true);

        Role user = new Role("user");
        user.setId(2);
        user.setAdmin(false);

        List<Role> expected = new ArrayList<>(Arrays.asList(admin, user));

        assertThat(users.getRoles(), is(expected));
    }

    /**
     * Тест добавления новой роли.
     */
    @Test
    public void whenAddRoleThenReceiveRole() {
        Role expected = new Role("test");
        expected.setId(3);
        expected.setAdmin(false);

        users.addRole(expected);

        assertThat(users.getRoles().get(2), is(expected));
    }

    /**
     * Тест редактирования роли.
     */
    @Test
    public void whenUpdateRoleThenReceiveUpdatedRole() {
        Role role = new Role("test");
        role.setId(3);
        role.setAdmin(false);

        users.addRole(role);
        Role updated = new Role("updated");
        updated.setId(3);
        updated.setAdmin(true);

        users.updateRole(updated);

        assertThat(users.getRoles().get(2), is(updated));
    }

    /**
     * Тест удаления роли.
     */
    @Test
    public void whenDeleteRoleThenRoleListContainNoRole() {
        Role role = new Role("test");
        role.setId(3);
        role.setAdmin(false);

        users.addRole(role);
        users.deleteRole(role);

        assertThat(users.getRoles().contains(role), is(false));
    }
    /**
     * Тест поиска роли по ID.
     */
    @Test
    public void whenSearchRoleByIDThenReceiveRole() {
        Role role = new Role("test");
        role.setId(3);
        role.setAdmin(false);

        users.addRole(role);

        assertThat(users.findRole(role.getId()), is(role));
    }

    @Test
    public void getCountries() {
        Map<Integer, String> countries = users.getCountries();
        System.out.println(countries);
    }

    @Test
    public void getAllCities() {
        Map<Integer, String> russia = users.getCities(1);
        System.out.println(russia);
        Map<Integer, String> ukraine = users.getCities(2);
        System.out.println(ukraine);
        Map<Integer, String> belarus = users.getCities(3);
        System.out.println(belarus);
        Map<Integer, String> kazakhstan = users.getCities(4);
        System.out.println(kazakhstan);
    }



}
