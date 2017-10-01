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
    public void whenAddElementsToTreeThenRecieveThemInList() {
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

    /**
     * Проверка, является ли созданное дерево двоичным.
     */
    @Test
    public void whenTreeIsBinaryThenCheckReturnsTrue() {
        Tree<Integer> tree = new Tree<>();
        tree.add(8, 5);
        tree.add(8, 2);
        tree.add(5, 4);
        tree.add(5, 10);
        tree.add(2, 22);
        tree.add(2, 23);
        assertThat(tree.isBinary(), is(true));
    }
}
