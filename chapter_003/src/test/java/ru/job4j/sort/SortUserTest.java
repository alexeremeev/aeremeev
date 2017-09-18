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
     * Тест для трех пользователей, переданных в TreeSet.
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
    /**
     * Тест для четырех пользователей, сортировка по длине имени.
     */
    @Test
    public void trySortUsersByNameLength() {
        SortUser sortUser = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User("Alexey", 31), new User("Petr", 30),
                new User("Oleg", 25), new User("Alexey", 42)));
        assertThat(sortUser.sortNameLength(list).toString(), is("[User{name='Petr', age=30}, User{name='Oleg',"
                 + " age=25}, User{name='Alexey', age=31}, User{name='Alexey', age=42}]"));
    }
    /**
     * Тест для четырех пользователей, сортировка по длине имени и возрасту.
     */
    @Test
    public void trySortUsersByAllFields() {
        SortUser sortUser = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User("Alexey", 31), new User("Ivan", 30),
                new User("Ivan", 25), new User("Alexey", 42)));
        assertThat(sortUser.sortByAllFields(list).toString(), is("[User{name='Ivan', age=25}, User{name='Ivan',"
                + " age=30}, User{name='Alexey', age=31}, User{name='Alexey', age=42}]"));
    }
}
