package ru.job4j.immutable.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ImmutableListTest {

    @Test
    public void whenCreateImmutableListFromListThenGetNewList() {
        List<Integer> original = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ImmutableList<Integer> list = new ImmutableList<Integer>(original);
        assertTrue(original.get(0) == list.get(0));
    }

    @Test
    public void whenCreateImmutableListFromArrayThenGetNewList() {
        Integer[] original = new Integer[] {1, 2, 3, 4, 5};
        ImmutableList<Integer> list = new ImmutableList<Integer>(original);
        assertTrue(Arrays.equals(original, list.toArray()));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void whenTryModifyImmutableListThenGetUnsupportedException() {
        ImmutableList<Integer> list = new ImmutableList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        list.add(1);
        list.remove(1);
        list.clear();
        //etc..
    }
}