package ru.job4j.structures.queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PriorityQueueTest {

    @Test
    public void whenAddItemsToPriorityQueueThenGetMinValue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(2);
        queue.add(1);
        queue.add(0);
        queue.add(1);

        assertTrue(queue.peek() == 0);
        assertTrue(queue.size() == 5);
    }

    @Test
    public void whenRemoveItemsFromQueueThenGetCorrectSizeAndMin() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(2);
        queue.add(1);
        queue.add(0);
        queue.add(5);

        queue.remove(0);
        queue.remove(1);

        assertTrue(queue.peek() == 2);
        assertTrue(queue.size() == 3);
    }

    @Test
    public void whenPollItemsFromPriorityQueueThenGetSortedList() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(2);
        queue.add(1);
        queue.add(0);
        queue.add(1);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 1, 2, 3));

        List<Integer> actual = new ArrayList<>();
        while (!queue.isEmpty()) {
            actual.add(queue.poll());
        }

        assertThat(actual, is(expected));
    }

}