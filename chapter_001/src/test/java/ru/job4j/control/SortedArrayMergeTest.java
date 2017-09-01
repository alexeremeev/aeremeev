package ru.job4j.control;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса SortedArrayMerge.
 */
public class SortedArrayMergeTest {
    /**
     * Сливаем {1, 2, 3, 4} и {2, 4, 6, 8, 9} в {1, 2, 2, 3, 4, 4, 6, 8, 9}.
     */
    @Test
    public void mergeTwoArrays() {
        SortedArrayMerge sortedArrayMerge = new SortedArrayMerge();
        int[] firstArray = {1, 2, 3, 4};
        int[] secondArray = {2, 4, 6, 8, 9};
        int[] expected = {1, 2, 2, 3, 4, 4, 6, 8, 9};
        assertThat(sortedArrayMerge.merge(firstArray, secondArray), is(expected));
    }

}
