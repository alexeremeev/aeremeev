package ru.job4j.iterator;

import java.util.Iterator;

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
        boolean exist = false;
        for (int i = index; i != values.length; i++) {
            for (int j = 2; j != values[i]; j++) {
                if (values[i] % j == 0) {
                    break;
                }
                exist = true;
            }
            if (exist) {
                break;
            }
        }
        return exist;
    }

    /**
     * Переопределенный next.
     * @return простое число из массива.
     */
    @Override
    public Object next() {
        int result = 0;
        for (int i = index; i != values.length; i++) {
            for (int j = 2; j != values[i]; j++) {
                if (values[i] % j == 0) {
                    break;
                }
                result = values[i];
            }
            if (result == values[i]) {
                index = ++i;
                break;
            }
        }
        return result;
    }
}
