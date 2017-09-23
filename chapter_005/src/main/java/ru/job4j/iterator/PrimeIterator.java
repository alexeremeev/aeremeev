package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class PrimeIterator - итератор для перебора простых чисел в массиве.
 */
public class PrimeIterator implements Iterator {
    /**
     * Входной массив.
     */
    private final int[] values;
    /**
     * Индекс в массиве.
     */
    private int index = 0;

    /**
     * Конструктор.
     * @param values входной массив.
     */
    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * Переопределенный hasNext.
     * @return true, если в массиве еще остались простые числа.
     */
    @Override
    public boolean hasNext() {
        return getIndexOfNextPrime() != -1;
    }

    /**
     * Переопределенный next.
     * @return простое число из массива.
     */
    @Override
    public Object next() {
        if (getIndexOfNextPrime() != -1) {
            int result = values[getIndexOfNextPrime()];
            index = getIndexOfNextPrime() + 1;
            return result;
        } else {
            throw new NoSuchElementException("No more prime numbers in this collection!");
        }

    }

    /**
     * Метод получения индекса следующего простого числа в массиве.
     * @return индекс следующего просто числа из массива или -1, если такого нет.
     */
    public int getIndexOfNextPrime() {
        int result = -1;
        for (int i = index; i != values.length; i++) {
            for (int j = 2; j != values[i]; j++) {
                if (values[i] % j == 0) {
                        break;
                }
                result = i;
            }
            if (result != -1) {
                break;
            }
        }
        return result;
    }
}
