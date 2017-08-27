package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса Counter.
 */
public class CounterTest {
    /**
     * Тест для ряда от 1 до 10, ожидаем результат 30.
     */
    @Test
    public void summOfEvenNumbers() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}
