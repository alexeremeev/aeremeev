package ru.job4j.tree;

/**
 * interface SimpleBinaryTree - интерфейс простого бинарного дерева.
 * @param <E> дженерик.
 */
public interface SimpleBinaryTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавление элемента в дерево.
     * @param value элемента.
     * @return true, если добавлено.
     */
    boolean add(E value);
}
