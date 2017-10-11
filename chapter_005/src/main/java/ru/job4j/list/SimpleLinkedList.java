//CHECKSTYLE.OFF
package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class SimpleLinkedList - реализация простого односвязного списка.
 * @param <E> дженерик.
 */
@ThreadSafe
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Размер списка.
     */
    protected int size = 0;
    /**
     * Головная нода.
     */
    protected Node<E> head;
    /**
     * Крайняя нода.
     */
    protected Node<E> tail;

    /**
     * Геттер элемента головной ноды.
     * @return элемент головной ноды.
     */
    public E getHeadElement() {
        return head.getElement();
    }
    /**
     * Геттер элемента крайней ноды.
     * @return элемент крайней ноды.
     */
    public E getTailElement() {
        return tail.getElement();
    }

    /**
     * Нода, хранящая данные элемента списка.
     * @param <E> дженерик.
     */
     protected class Node<E> {
        /**
         * Данные элемента списка.
         */
        protected E element;
        /**
         * Ссылка на следующую ноду.
         */
        protected Node<E> next;

        /**
         * Геттер следующей ноды.
         * @return следующая нода.
         */
        public Node<E> getNext() {
            return this.next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Геттер элемента ноды.
         * @return хранимиый элемент ноды.
         */
        public E getElement() {
            return this.element;
        }

        /**
         * Конструктор.
         * @param element значение элемента.
         */
        public Node(E element) {
            this.element = element;
        }
    }

    /**
     * Добавление в список.
     * @param e добавляемый элемент.
     * @return true при добавлении.
     */
    @Override
    @GuardedBy("this")
    public synchronized boolean add(E e) {

        Node<E> node = new Node<>(e);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    /**
     * Получуние элемента из списка по индексу.
     * @param index индекс элемента.
     * @return элемент.
     */
    @Override
    @GuardedBy("this")
    public synchronized E get(int index) {
        Node<E> current = head;
        if (index < size) {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }
        return current.getElement();
    }

    /**
     * Удаление ноды из списка по значению элемента.
     * @param value элемент.
     */
    @GuardedBy("this")
    public synchronized void remove(E value) {
        Node<E> previous = null;
        Node<E> current = head;

        while (current != null) {
            if (current.getElement().equals(value)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null) {
                        tail = previous;
                    }
                } else {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                }
                size--;
                break;
            }
            previous = current;
            current = current.next;
        }
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
//CHECKSTYLE.ON