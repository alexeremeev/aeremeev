package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class SimpleLinkedList - реализация простого односвязного списка.
 * @param <E> дженерик.
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Размер списка.
     */
    private int size = 0;
    /**
     * Головная нода.
     */
    private Node<E> head;
    /**
     * Крайняя нода.
     */
    private Node<E> tail;

    /**
     * Нода, хранящая данные элемента списка.
     * @param <E> дженерик.
     */
    private class Node<E> {
        /**
         * Данные элемента списка.
         */
        private E element;
        /**
         * Ссылка на следующую ноду.
         */
        private Node<E> next;

        /**
         * Геттер следующей ноды.
         * @return следующая нода.
         */
        private Node<E> getNext() {
            return this.next;
        }

        /**
         * Геттер элемента ноды.
         * @return хранимиый элемент ноды.
         */
        private E getElement() {
            return this.element;
        }

        /**
         * Конструктор.
         * @param element значение элемента.
         */
        private Node(E element) {
            this.element = element;
        }
    }

    /**
     * Добавление в список.
     * @param e добавляемый элемент.
     */
    @Override
    public void add(E e) {
        Node<E> node = new Node<>(e);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Получуние элемента из списка по индексу.
     * @param index индекс элемента.
     * @return элемент.
     */
    @Override
    public E get(int index) {
        Node<E> current = head;
        if (index < size) {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }
        return current.getElement();
    }

    /**
     * Итератор.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
           private int currentIndex = 0;
           private Node<E> currentNode = null;

            @Override
            public boolean hasNext() {
                return currentIndex < size && currentNode != tail;
            }

            @Override
            public E next() {
                if (size == 0) {
                    throw new NoSuchElementException("Simple Linked List is Empty!");
                }
                if (currentNode == null) {
                    currentNode = head;
                    return head.getElement();
                }
                if (currentNode.getNext() == null) {
                    throw new NoSuchElementException("No such element in Simple Linked List");
                }
                    currentNode = currentNode.getNext();
                    currentIndex++;
                return currentNode.getElement();
            }
        };
        return it;
    }
}
