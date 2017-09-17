package ru.job4j.sort;

import java.util.List;
import java.util.TreeSet;

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
        TreeSet<User> set = new TreeSet<>();
        for (User user: list) {
            set.add(user);
        }
        return set;
    }
}
