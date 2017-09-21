package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class EvenIterator - итератор для перебора четных чисел в массиве.
 */
public class EvenIterator implements Iterator {
    /**
     * Входной массив.
     */
    private final int[] values;
    /**
     * Индекс элемента в массиве.
     */
    private int index = 0;

    /**
     * Конструктор.
     * @param values входной массив.
     */
    public EvenIterator(int[] values) {
        this.values = values;
    }

    /**
     * Переопределенный hasNext.
     * @return true, если в массиве еще остались четные числа.
     */
    @Override
    public boolean hasNext() {
        return pass() != -1;
    }

    /**
     * Переопределенный next.
     * @return только четные числа из массива.
     */
    @Override
    public Object next() {
        if (pass() != -1) {
            int result = values[pass()];
            index = pass() + 1;
            return result;
        } else {
            throw new NoSuchElementException("No more elements in this collection!");
        }
    }

    /**
     * Метод прохода по массиву с целью получения следующего четного числа.
     * @return индекс следующего четного числа в массиве или -1, если такого нет.
     */
    private int pass() {
        int result = -1;
        for (int i = index; i != values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
