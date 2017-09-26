package ru.job4j.list;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса LinkedCycle.
 */
public class LinkedCycleTest {
    /**
     * Создали 5 нод, зациклили со 2й по 5ю.
     */
    @Test
    public void thenHaveFourNodesWitchCycleThenHasCycleReturnTrue() {
        LinkedCycle lc = new LinkedCycle();
        LinkedCycle.Node<Integer> first = new LinkedCycle.Node<>(1);
        LinkedCycle.Node<Integer> two = new LinkedCycle.Node<>(2);
        LinkedCycle.Node<Integer> third = new LinkedCycle.Node<>(3);
        LinkedCycle.Node<Integer> four = new LinkedCycle.Node<>(4);
        LinkedCycle.Node<Integer> fifth = new LinkedCycle.Node<>(5);
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(fifth);
        fifth.setNext(two);

        assertThat(lc.hasCycle(two), is(true));
    }

}
