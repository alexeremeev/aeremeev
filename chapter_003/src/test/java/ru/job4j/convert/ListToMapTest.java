package ru.job4j.convert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class ListToMapTest - тест для ListToMap.
 */
public class ListToMapTest {
    /**
     * Тест конвертации списка в карту.
     */
    @Test
    public void tryConvertListToMap() {
        ListToMap listToMap = new ListToMap();
        List<User> users = new ArrayList<User>();
        User alex = new User(1, "Alex", "Moscow");
        User petr = new User(2, "Petr", "Sbp");
        users.add(alex);
        users.add(petr);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, alex);
        expected.put(2, petr);
        assertThat(listToMap.process(users), is(expected));
    }

}
