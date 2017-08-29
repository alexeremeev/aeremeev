package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса RotateArray.
 */
public class RotateArrayTest {
    /**
     * Тест для массива 2 на 2.
     */
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] array = {{1, 2}, {3, 4}};
        int[][] arrayExpected = {{3, 1}, {4, 2}};
        assertThat(rotateArray.rotate(array), is(arrayExpected));
    }

    /**
     * Тест для массива 3 на 3.
     */
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arrayExpected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(rotateArray.rotate(array), is(arrayExpected));
    }
}