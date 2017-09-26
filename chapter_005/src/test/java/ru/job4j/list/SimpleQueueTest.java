package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса SimpleQueue.
 */
public class SimpleQueueTest {
    /**
     * Добавление четырех элементов и вытягивание трех.
     */
    @Test
    public void whenAddElementsToSimpleStackThenPullLastElement() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();

        queue.push(11);
        queue.push(22);
        queue.push(33);
        queue.push(44);
        queue.poll();
        queue.poll();

        assertThat(queue.poll(), is(33));
    }
}