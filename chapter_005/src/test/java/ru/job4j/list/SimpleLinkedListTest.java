package ru.job4j.list;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса SimpleLinkedList.
 */
public class SimpleLinkedListTest {
    /**
     * Тесты методов add() и get().
     */
    @Test
    public void whenAddThreeElementsToSimpleLinkedListThenGetSecond() {
        SimpleLinkedList<Integer> simpleLL = new SimpleLinkedList<>();

        simpleLL.add(1);
        simpleLL.add(2);
        simpleLL.add(3);

        assertThat(simpleLL.get(1), is(2));
    }

    /**
     * Тест метода remove().
     */
    @Test
    public void whenRemoveSecondElementFromSimpleLinkedListThenGetThird() {
        SimpleLinkedList<Integer> simpleLL = new SimpleLinkedList<>();

        simpleLL.add(1);
        simpleLL.add(2);
        simpleLL.add(3);

        simpleLL.remove(2);

        assertThat(simpleLL.get(1), is(3));
    }

}
