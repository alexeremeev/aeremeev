package ru.job4j.iterator;

import java.util.Iterator;

/**
 * class MatrixIterator - реализация итератора для двумерного массива, значения по порядку.
 */
public class MatrixIterator implements Iterator {

    /**
     * Принимаемый массив.
     */
    private final int[][] values;
    /**
     * Счетчик рядов.
     */
    private int row = 0;
    /**
     * Счетчик колонок.
     */
    private int column = 0;

    /**
     * Конструктор.
     * @param values принимаемый массив.
     */
    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    /**
     * Переопределенный hasNext.
     * @return true, eсли в массиве остались элементы.
     */
    @Override
    public boolean hasNext() {
        return row < values.length && column < values[row].length;
    }

    /**
     * Переопределенный next.
     * @return элемент массива.
     */
    @Override
    public Object next() {
        int result = values[row][column++];
        if (values[row].length <= column) {
            row++;
            column = 0;
        }
        return result;
    }
}
