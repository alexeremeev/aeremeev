package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * class SortUser - сортировка User.
 */
public class SortUser {
    /**
     * Сортировка user по возрасту из списка list.
     * @param list входной список.
     * @return TreeSet с отсортированными user.
     */
    public TreeSet<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Сортировка по длине имени.
     * @param list входной список.
     * @return list, с отсортированными по длине имени пользователями.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(Comparator.comparing(user -> user.getName().length()));
        return list;
    }

    /**
     * Сортировка по длине имени и возрасту.
     * @param list входной список.
     * @return ist, с отсортированными по длине имени и возрасту пользователями.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(Comparator.comparing((User user) -> user.getName().length()).thenComparing(User::getAge));
        return list;
    }
}
