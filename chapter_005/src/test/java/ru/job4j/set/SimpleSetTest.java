package ru.job4j.set;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса SimpleSet.
 */
public class SimpleSetTest {
    /**
     * Добавление нескольких элементов с попыткой добавить дубль.
     */
    @Test
    public void whenAddToSimpleSetThenPrintNoDoubles() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();

        simpleSet.add(33);
        simpleSet.add(11);
        simpleSet.add(22);

        boolean addDouble = simpleSet.add(11);

        for (Integer value : simpleSet) {
            System.out.println(value);
        }
        assertThat(addDouble, is(false));
    }
}
