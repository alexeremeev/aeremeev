package ru.job4j.list;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса SimpleStack.
 */
public class SimpleStackTest {
    /**
     * Добавление трех элементов и вытягивание двух.
     */
    @Test
    public void whenAddElementsToSimpleStackThenPullLastElement() {
        SimpleStack<Integer> stack = new SimpleStack<>();

        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.poll();

        assertThat(stack.poll(), is(22));
    }
}
