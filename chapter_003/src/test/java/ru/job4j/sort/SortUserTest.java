package ru.job4j.sort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса SortUser.
 */
public class SortUserTest {
    /**
     * Тест для трех пользователей.
     */
    @Test
    public void trySortUsersByAgeInTreeSet() {
        SortUser sortUser = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User("Alex", 31), new User("Petr", 30),
                new User("Oleg", 25)));
        TreeSet<User> set = sortUser.sort(list);
        assertThat(set.toString(),
                is("[User{name='Oleg', age=25}, User{name='Petr', age=30}, User{name='Alex', age=31}]"));

    }
}
