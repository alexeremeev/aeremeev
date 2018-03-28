package ru.job4j.algorithms.shuffle;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShuffleSortTest {

    @Test
    public void whenSortListViaShuffleThenGetSortedList() {
        List<User> original = new ArrayList<>(Arrays.asList(new User(5,"Yaroslav"),
                new User(1, "Alex"), new User(3, "Sergey"), new User(2, "Dmitriy"),
                new User(4, "Peter"), new User(0, "Alexander")));
        new Sort<User>().shuffleSort(original);

        List<User> expected = new ArrayList<>(Arrays.asList(new User(1, "Alex"),
                new User(0, "Alexander"), new User(2, "Dmitriy"), new User(4, "Peter"),
                new User(3, "Sergey"), new User(5,"Yaroslav")));
        assertThat(original, is(expected));
    }

    @Test
    public void whenSortListViaShakerThenGetSortedList() {
        List<User> original = new ArrayList<>(Arrays.asList(new User(5,"Yaroslav"),
                new User(1, "Alex"), new User(2, "Dmitriy"),
                new User(4, "Peter"), new User(0, "Alexander"), new User(3, "Sergey")));
        new Sort<User>().shakerSort(original);

        List<User> expected = new ArrayList<>(Arrays.asList(new User(1, "Alex"),
                new User(0, "Alexander"), new User(2, "Dmitriy"), new User(4, "Peter"),
                new User(3, "Sergey"), new User(5,"Yaroslav")));
        assertThat(original, is(expected));
    }

    private class User implements Comparable<User> {

        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            return this.name.compareTo(o.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }
}