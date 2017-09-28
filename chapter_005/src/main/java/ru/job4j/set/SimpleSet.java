package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class SimpleSet<E> - простой Set на основе массива.
 * @param <E> дженерик.
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * Массив для хранения объектов.
     */
    private Object[] container = new Object[1];
    /**
     * Размер массива.
     */
    private int size = 0;

    /**
     * Добавление объекта в множество.
     * @param value объект.
     * @return true если добавлен, false, если это дубль.
     */
    public boolean add(E value) {
        boolean result = false;
        if (size == 0) {
            container[size++] = value;
            return true;
        } else {
            if (!contains(value)) {
                container = Arrays.copyOf(container, size + 1);
                container[size++] = value;
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
        boolean result = false;
        for (int index = 0; index < size; index++) {
            if (container[index].equals(value)) {
                result = true;
                break;
            }
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
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) container[currentIndex++];
                } else {
                    throw new NoSuchElementException("No such element in Simple Set!");
                }
            }
        };
        return it;
    }
}
