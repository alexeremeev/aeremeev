package ru.job4j.tree;

/**
 * Интерфейс SimpleTree - интерфейс простого дерева.
 * @param <E>
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавление родительского и дочернего элемента в дерево.
     * @param parent родительский элемент.
     * @param child дочерний элемент.
     * @return true, если элемент добавлен.
     */
    boolean add(E parent, E child);
}
