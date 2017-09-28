package ru.job4j.set;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса SimpleLinkedSet.
 */
public class SimpleLinkedSetTest {
    /**
     * Добавление элементов в множество и попытка добавить дубль.
     */
    @Test
    public void whenAddToSimpleLinkedSetThenPrintNoDoubles() {
        SimpleLinkedSet<Integer> sls = new SimpleLinkedSet<>();

        sls.add(33);
        sls.add(22);
        sls.add(11);

        boolean addDouble = sls.add(22);

        for (Integer value : sls) {
            System.out.println(value);
        }

        assertThat(addDouble, is(false));
    }
}
