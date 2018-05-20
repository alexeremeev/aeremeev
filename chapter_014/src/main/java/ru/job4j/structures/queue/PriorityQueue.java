package ru.job4j.structures.queue;

import ru.job4j.structures.linked.SimpleLinkedList;

public class PriorityQueue<E extends Comparable<? super E>> {

    private SimpleLinkedList<E> storage = new SimpleLinkedList<>();

    public void add(E value) {
        this.storage.addWithSort(value);
    }

    public E peek() {
        E result = null;
        if (this.storage.size() > 0) {
            result = this.storage.getHeadElement();
        }
        return result;
    }

    public E poll() {
        E result = null;
        if (this.storage.size() > 0) {
            result = this.storage.getHeadElement();
            this.storage.remove(result);
        }
        return result;
    }

    public void remove(E value) {
        this.storage.remove(value);
    }

    public int size() {
        return this.storage.size();
    }

    public boolean isEmpty() {
        return this.storage.size() == 0;
    }

}
