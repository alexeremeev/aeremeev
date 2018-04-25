package ru.job4j.structures.linked;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * class SimpleLinkedList - реализация простого односвязного списка.
 * @param <E> дженерик.
 */
public class SimpleLinkedList<E extends Comparable<? super E>> implements SimpleContainer<E> {
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
    private class Node<E extends Comparable<? super E>> {
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

        public void setNext(Node<E> next) {
            this.next = next;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(getElement(), node.getElement())
                    && Objects.equals(getNext(), node.getNext());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getElement(), getNext());
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("element=").append(element);
            sb.append(", next=").append(next);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Добавление в список.
     * @param e добавляемый элемент.
     * @return true при добавлении.
     */
    @Override
    public boolean add(E e) {

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
     * Удаление ноды из списка по значению элемента.
     * @param value элемент.
     */
    public void remove(E value) {
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

    public int size() {
        return this.size;
    }

    public void mergeSort() {
        this.mergeSort(this.head);
    }

    public Node<E> mergeSort(Node<E> headOriginal) {

        if (headOriginal == null || headOriginal.next == null) {
            return headOriginal;
        }

        Node<E> a = headOriginal;
        Node<E> b = headOriginal.next;
        while ((b != null) && (b.next != null)) {
            headOriginal = headOriginal.next;
            b = (b.next).next;
        }
        b = headOriginal.next;
        headOriginal.next = null;
        return merge(mergeSort(a), mergeSort(b));
    }

    public Node<E> merge(Node<E> a, Node<E> b) {
        Node<E> head = new Node<>(null);
        Node<E> c = head;
        while ((a != null) && (b != null)) {
            if (a.element.compareTo(b.element) <= 0) {
                if (this.head.element.compareTo(a.element) > 0) {
                    this.head = a;
                }
                c.next = a;
                c = a;
                a = a.next;
                if (this.tail.element.compareTo(c.element) < 0) {
                    this.tail = c;
                }
            } else {
                if (this.head.element.compareTo(b.element) > 0) {
                    this.head = b;
                }
                c.next = b;
                c = b;
                b = b.next;
                if (this.tail.element.compareTo(c.element) < 0) {
                    this.tail = c;
                }
            }
        }
        c.next = (a == null) ? b : a;
        return head.next;
    }

    public void removeDuplicates() {
        Node<E> current = this.head;
        Node<E> nextNode;
        while (current != null && current.next != null) {
            if (current.element.equals(current.next.element)) {
                nextNode = current.next.next;
                current.next = null;
                current.next = nextNode;
                this.size--;
            } else {
                current = current.next;
            }
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
                return currentIndex < size && currentNode != tail.next;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (currentNode == null) {
                        currentNode = head;
                        return head.getElement();
                    }
                    currentNode = currentNode.getNext();
                    currentIndex++;
                    return currentNode.getElement();
                } else {
                    throw new NoSuchElementException("No such element in Simple Linked List");
                }
            }
        };
        return it;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleLinkedList<?> that = (SimpleLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, head, tail);
    }
}
