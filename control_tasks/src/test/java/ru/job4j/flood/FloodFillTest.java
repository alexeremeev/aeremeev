package ru.job4j.flood;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест FloodFill.
 */
public class FloodFillTest {
    /**
     * Тест на подготовленном массиве, самая большая фигура в нижнем правом углу массива.
     */
    @Test
    public void findLargestFigure() {
        FloodFill ff = new FloodFill();

        int[][] field = new int[][] {
                {0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                {0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 1, 1, 1, 1, 1},
        };
        List<String> expected = new ArrayList<>(Arrays.asList("(4, 5)", "(5, 5)", "(5, 6)", "(5, 7)", "(5, 9)",
                "(6, 5)", "(6, 7)", "(6, 9)", "(7, 5)", "(7, 7)", "(7, 9)", "(8, 5)", "(8, 7)", "(8, 9)", "(9, 5)",
                "(9, 6)", "(9, 7)", "(9, 8)", "(9, 9)"));

        List<String> result = ff.largestFilledObject(field);
        result.sort(Comparator.naturalOrder());

        assertThat(result, is(expected));
    }

}