package ru.job4j.map;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тесты для наблюдения за hashcode() и equals().
 */
public class UserMapTest {
    /**
     * Добавляем пользователей в карту.
     */
    @Test
    public void addToMap() {
        Map<User, Object> map = new HashMap<>();
        User first = new User("Alex", 1, 17, 3, 1986);
        User second = new User("Alex", 1, 17, 3, 1986);
        map.put(first, null);
        map.put(second, null);
        System.out.println(map);
    }
}
