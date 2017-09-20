package ru.job4j.iterator;

import java.util.Iterator;

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
        boolean exist = false;
        for (int i = index; i != values.length; i++) {
            if (values[i] % 2 == 0) {
                exist = true;
            }
        }
        return exist;
    }

    /**
     * Переопределенный next.
     * @return только четные числа из массива.
     */
    @Override
    public Object next() {
        int result = 0;
        for (int i = index; i != values.length; i++) {
            if (values[i] % 2 == 0) {
                result = values[i];
                index = ++i;
                break;
            }
        }
        return result;
    }
}
