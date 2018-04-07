package ru.job4j.algorithms.binary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTest {
    @Test
    public void whenEvaluateUnsortedListThenResultIsFalse() {
        List<Integer> original = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7, 6));
        assertFalse(new BinarySearch<Integer>().isSort(original));
    }

    @Test
    public void whenTryToSearchInUnsortedListWhenResultIsMinusOne() {
        List<Integer> original = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7, 6));
        assertTrue(new BinarySearch<Integer>().findIndex(4, original) == -1);
    }

    @Test
    public void whenSeachThroughSortedListThenGetValueIndex() {
        List<Integer> original = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        assertTrue(new BinarySearch<Integer>().findIndex(8, original) == 7);
    }
}