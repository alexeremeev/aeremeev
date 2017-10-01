package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

/**
 * class SimpleLinkedSet простой Set на связном однонаправленном списке.
 * @param <E> дженерик.
 */
public class SimpleLinkedSet<E> extends SimpleLinkedList<E> {
    /**
     * Добавление в множество.
     * @param value добавляемый элемент.
     * @return true при добавлении.
     */
    @Override
    public boolean add(E value) {
        boolean result = false;
        if(!contains(value)) {
            super.add(value);
            result = true;
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
}
