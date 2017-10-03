package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * class BinaryTree - простая реализация бинарного дерево.
 * @param <E> дженерик.
 */
public class BinaryTree<E extends Comparable<E>> implements SimpleBinaryTree<E> {
    /**
     * Головная нода, корень дерева.
     */
    private Node<E> head;

    /**
     * class Node - нода, внутренний контейнера для хранения элементов.
     * @param <E> дженерик.
     */
    private class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        /**
         * Значение добавленного элемента.
         */
        private E value;
        /**
         * Левая нода, хранятся значения <= value.
         */
        private Node<E> left;
        /**
         * Правая нода, хранятся значения > value.
         */
        private Node<E> right;

        /**
         * Конструктор.
         * @param value значение добавляемого элемента.
         */
        private Node(E value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node<E> node) {
            return this.value.compareTo(node.value);
        }
    }

    /**
     * Добавление элемента в дерево.
     * @param value элемента.
     * @return true, если добавлено.
     */
    @Override
    public boolean add(E value) {
        boolean result;
        if (head == null) {
            head = new Node<>(value);
            result = true;
        } else {
            result = findBranch(head, new Node<>(value));
        }
        return result;
    }

    /**
     * Метода поиска и вставки элемента в ветвь дерева.
     * @param head головная нода.
     * @param value значение для вставки.
     * @return true, если ветвь найдена и значение вставлено.
     */
    private boolean findBranch(Node<E> head, Node<E> value) {
        boolean result = false;
        if (head != null) {
            if (head.compareTo(value) >= 0) {
                if (head.left == null) {
                    head.left = value;
                    result = true;
                } else {
                    result = findBranch(head.left, value);
                }
            } else {
                if (head.right == null) {
                    head.right = value;
                    result = true;
                } else {
                    result = findBranch(head.right, value);
                }
            }
        }
        return result;
    }

    /**
     * Метода заполняет список всеми значениями дерева в возрастающем порядке.
     * @param head головная нода.
     * @param values список значений.
     */
    private void getListOfValues(Node<E> head, List<E> values) {
        if (head != null) {
            getListOfValues(head.left, values);
            values.add(head.value);
            getListOfValues(head.right, values);
        }
    }

    /**
     * Итератор.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private List<E> values;
            private int index;

            @Override
            public boolean hasNext() {
                if (values == null) {
                    values = new ArrayList<>();
                    getListOfValues(head, values);
                }
                return index < values.size();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return values.get(index++);
                } else {
                    throw new NoSuchElementException("No such element in Binary Tree!");
                }

            }
        };
        return it;
    }
}
