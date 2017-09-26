package ru.job4j.list;

/**
 * Class SimpleStack - простая очередь на базе SimpleLinkedList.
 * @param <E> дженерик.
 */
public class SimpleQueue<E> {
    /**
     * Хранилище очереди.
     */
    private SimpleLinkedList<E> queue = new SimpleLinkedList<>();

    /***
     * Постановка элемента в очередь.
     * @param value элемент.
     */
    public void push(E value) {
        queue.add(value);
    }

    /**
     * Получение первого элемента из очереди.
     * @return первый элемента из очереди.
     */
    public E poll() {
        Object swap = queue.getHeadElement();
        queue.remove((E) swap);
        return (E) swap;
    }

}