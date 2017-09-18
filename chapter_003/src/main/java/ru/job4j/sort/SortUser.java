package ru.job4j.sort;

import java.util.Comparator;
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

    /**
     * Сортировка по длине имени.
     * @param list входной список.
     * @return list, с отсортированными по длине имени пользователями.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return Integer.compare(o1.getName().length(), o2.getName().length());
                    }
                }
        );
        return list;
    }

    /**
     * Сортировка по длине имени и возрасту.
     * @param list входной список.
     * @return ist, с отсортированными по длине имени и возрасту пользователями.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int result = Integer.compare(o1.getName().length(), o2.getName().length());
                        return result != 0 ? result : o1.getAge().compareTo(o2.getAge());
                    }
                }
        );
        return list;
    }
}
