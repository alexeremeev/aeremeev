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
     * Добавление объекта в множество c двоичным поиском дубля.
     * @param value объект.
     * @return true если добавлен, false, если это дубль.
     */
    public boolean addBinary(E value) {
        boolean result = false;
        if (size == 0) {
            container[size++] = value;
            return true;
        } else {
            if (!binaryContains(value)) {
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
        Arrays.sort(container);
        for (int index = 0; index < size; index++) {
            if (container[index].equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод проверяет, есть ли такой объект в множенстве с помощью двоичного поиска.
     * @param value объект.
     * @return true, если есть.
     */
    public boolean binaryContains(E value) {
        Arrays.sort(container);
        boolean result = false;
        int lowerBound = 0;
        int upperBound = container.length - 1;
        int current;
        while (true) {
            current = (lowerBound + upperBound) / 2;
            if (container[current].equals(value)) {
                result = true;
                break;
            } else if (lowerBound > upperBound) {
                break;
            } else {
                Comparable currentValue = (Comparable) container[current];
                int comp = currentValue.compareTo(value);
                if (comp < 0) {
                    lowerBound = current + 1;
                } else {
                    upperBound = current - 1;
                }
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
