package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class SimpleLinkedSet простой Set на связном однонаправленном списке.
 * @param <E> дженерик.
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    /**
     * Размер списка.
     */
    private int size = 0;
    /**
     * Головная нода.
     */
    private Node<E> head;
    /**
     * Конечная нода.
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
     * Добавление объекта в множество.
     * @param value объект.
     * @return true если добавлен, false, если это дубль.
     */
    public boolean add(E value) {
        boolean result = false;
        Node<E> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
            size++;
            result = true;
        } else {
            if (!contains(value)) {
                tail.next = node;
                tail = node;
                size++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод проверяет, есть ли такой объект в множенстве.
     * @param value объект.
     * @return true, если есть.
     */
    public boolean contains(E value) {
        Node<E> current = head;
        boolean result = false;
        for (int index = 0; index < size; index++) {
            if (current.getElement().equals(value)) {
                result = true;
                break;
            }
            current = current.getNext();
        }
        return result;
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
