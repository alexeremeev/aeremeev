package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса ArrayDuplicate.
 */
public class ArrayDuplicateTest {
    /**
     * Тест с использованием массива {"Привет", "Мир", "Привет", "Супер", "Мир"}.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] actual = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expected = {"Привет", "Мир", "Супер"};
        assertThat(arrayDuplicate.remove(actual), is(expected));
    }
}