package ru.job4j.tree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса BinaryTree.
 */
public class BinaryTreeTest {
    /**
     * Тест добавления элементов в дерево, их распределения по нему и итератора.
     */
    @Test
    public void whenAddToBinaryTreeWhenRecieveSortedValues() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(2);
        tree.add(3);
        tree.add(6);
        tree.add(9);
        List<Integer> result = new ArrayList<>();

        for (Integer value: tree) {
            result.add(value);
        }

        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 2, 3, 5, 6, 9));

        assertThat(result, is(expected));
    }

    /**
     * Тест инвертирования дерева с помощью DFS.
     */
    @Test
    public void whenSwappingViaDFSThenGetInvertedTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(2);
        tree.add(3);
        tree.add(6);
        tree.add(9);
        tree.swapViaDFS();
        List<Integer> result = new ArrayList<>();

        for (Integer value: tree) {
            result.add(value);
        }

        List<Integer> expected = new ArrayList<>(Arrays.asList(9, 6, 5, 3, 2, 2));

        assertThat(result, is(expected));
    }

    /**
     * Тест инвертирования дерева с помощью BFS.
     */
    @Test
    public void whenSwappingViaBFSThenGetInvertedTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(2);
        tree.add(3);
        tree.add(6);
        tree.add(9);
        tree.swapViaBFS();
        List<Integer> result = new ArrayList<>();

        for (Integer value: tree) {
            result.add(value);
        }

        List<Integer> expected = new ArrayList<>(Arrays.asList(9, 6, 5, 3, 2, 2));

        assertThat(result, is(expected));
    }

}
