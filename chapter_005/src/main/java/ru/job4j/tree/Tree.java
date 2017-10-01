package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * class Tree - простая реализация дерева.
 * @param <E> дженерик.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Голова или корень дерева.
     */
    private Node<E> head;

    /**
     * Нода дерева, хранящая родительское значение и список детей.
     * @param <E> дженерик.
     */
    private class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        /**
         * Списко дочерних нод.
         */
        private List<Node<E>> children;
        /**
         * Значение ноды.
         */
        private E value;

        /**
         * Конструктор.
         * @param value значение элемента.
         */
        private Node(E value) {
            this.value = value;
            this.children = new LinkedList<>();
        }

        /**
         * Сравнение нод по значению value.
         * @param node нода.
         * @return результат сравнения value.
         */
        @Override
        public int compareTo(Node<E> node) {
            return this.value.compareTo(node.value);
        }
    }

    /**
     * Поиск ноды в дереве по значению value.
     * @param head головная нода, с которой начинается поиск.
     * @param value значение для поиска.
     * @return нода со значением value.
     */
    private Node<E> findValue(Node<E> head, Node<E> value) {
        Node<E> searchResult = null;
        if (head != null) {
            if (head.compareTo(value) == 0) {
                searchResult = head;
            } else {
                for (Node<E> node : head.children) {
                    searchResult = findValue(node, value);
                    if (searchResult != null) {
                        break;
                    }
                }
            }
        }
        return searchResult;
    }

    /**
     * Добавление родительского и дочернего элемента в дерево.
     * @param parent родительский элемент.
     * @param child дочерний элемент.
     * @return true, если элемент добавлен.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> temp;
        if (head == null) {
            head = new Node<>(parent);
        }
        temp = findValue(head, new Node<>(parent));
        if (temp != null) {
                Node<E> infant = new Node<>(child);
                if (findValue(head, infant) == null) {
                    temp.children.add(infant);
                    result = true;
                }
        }
        return result;

    }

    /**
     * Получение списка всех значений дерева.
     * @param head головная нода.
     * @param values список, куда будут записаны значения.
     */
    private void getListOfValues(Node<E> head, List<E> values) {
        if (head != null) {
            values.add(head.value);
        }
        for (Node<E> node : head.children) {
            getListOfValues(node, values);
        }
    }

    /**
     * Итератор.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        Iterator it = new Iterator() {
            private List<E> values;
            private int index = 0;

                        @Override
            public boolean hasNext() {
                if (values == null) {
                    values = new LinkedList<>();
                    getListOfValues(head, values);
                }
                return index < values.size();
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return values.get(index++);
                } else {
                    throw new NoSuchElementException("No such element in Tree!");
                }
            }
        };
        return it;
    }

}

