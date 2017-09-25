package ru.job4j.generic;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты хранилища UserStore.
 */
public class UserStoreTest {
    /**
     * Добавление нового User.
     */
    @Test
    public void whenAddUserThenGetThisUser() {
        UserStore userStore = new UserStore(5);
        User user = new User("test user");
        userStore.add(user);
        assertThat(userStore.get(0), is(user));
    }

    /**
     * Обвноление Id у существующего User.
     */
    @Test
    public void whenUpdateUserThenGetNewUser() {
        UserStore userStore = new UserStore(5);
        User john = new User("test id");
        userStore.add(john);
        john.setId("new id");
        userStore.update(john);
        assertThat(userStore.get(0).getId(), is("new id"));
    }

    /**
     * Удаление User из хранилища.
     */
    @Test
    public void whenDeleteUserFromStoreThenGetNextUser() {
        UserStore userStore = new UserStore(5);
        User john = new User("John");
        User jane = new User("Jane");
        User jack = new User("Jack");
        userStore.add(john);
        userStore.add(jane);
        userStore.add(jack);
        userStore.remove(jane);

        assertThat(userStore.get(1), is(jack));
    }

}
