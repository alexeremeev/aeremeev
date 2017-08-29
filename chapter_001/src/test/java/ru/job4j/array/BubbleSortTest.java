package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса BubbleSort.
 */
public class BubbleSortTest {
    /**
     * Тест для массива {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bubble = new BubbleSort();
        int[] arrayExpected = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        int[] arrayActual = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        arrayActual = bubble.sort(arrayActual);
        assertThat(arrayActual, is(arrayExpected));
    }
}