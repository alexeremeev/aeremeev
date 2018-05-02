package ru.job4j.structures.linked;
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

    @Test
    public void whenUseSortOnUnsortedListAndRemoveDuplicatesThenGetSortedUniqueList() {
        SimpleLinkedList<Integer> original = new SimpleLinkedList<>();
        original.add(-1);
        original.add(-121);
        original.add(21);
        original.add(431);
        original.add(7);
        original.add(0);
        original.add(5);
        original.add(6);
        original.add(0);
        original.add(7);
        original.mergeSort();

        original.removeDuplicates();

        SimpleLinkedList<Integer> expected = new SimpleLinkedList<>();

        expected.add(-121);
        expected.add(-1);
        expected.add(0);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(21);
        expected.add(431);

        assertThat(original, is(expected));
    }

    @Test
    public void whenSumTwoLinkedListsThenGetResultList() {
        SimpleLinkedList<Integer> first = new SimpleLinkedList<>();
        first.add(1);
        first.add(2);
        first.add(3);
        first.add(2);
        first.add(1);
        SimpleLinkedList<Integer> second = new SimpleLinkedList<>();
        second.add(7);
        second.add(6);
        second.add(8);

        SimpleLinkedList<Integer> result = first.sumTwoList(first, second);

        SimpleLinkedList<Integer> expected = new SimpleLinkedList<>();
        expected.add(8);
        expected.add(8);
        expected.add(1);
        expected.add(3);
        expected.add(1);
        assertThat(result, is(expected));
    }
}
