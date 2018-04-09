package ru.job4j.structures.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NullifyTest {

    @Test
    public void whenNullifyArrayThenGetExpectedResult() {
        int[][] original = new int[][] {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };
        int[][] expected = new int[][] {
                {1, 0, 3},
                {0, 0, 0},
                {7, 0, 9}
        };

        new Nullify().nullifyArray(original);

        assertThat(original, is(expected));
    }

    @Test
    public void whenNullifyArrayWithSeveralZeroesThenGetExpectedResult() {
        int[][] original = new int[][] {
                {1, 2, 3, 4, 0},
                {4, 0, 6, 6, 8},
                {7, 8, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1}
        };
        int[][] expected = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {7, 0, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        new Nullify().nullifyArray(original);

        assertThat(original, is(expected));
    }


}