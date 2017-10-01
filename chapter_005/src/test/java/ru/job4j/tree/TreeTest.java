package ru.job4j.tree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса Tree.
 */
public class TreeTest {
    /**
     * Тест добавления элеменов и итератора.
     */
    @Test
    public void thenAddElementsToTreeThenRecieveThemInList() {
        Tree<Integer> tree = new Tree<>();
        tree.add(8, 1);
        tree.add(8, 2);
        tree.add(2, 4);
        List<Integer> result = new LinkedList<>();

        for (Integer value : tree) {
            result.add(value);
        }

        List<Integer> expected = new LinkedList<>(Arrays.asList(8, 1, 2, 4));

        assertThat(result, is(expected));
    }
}
