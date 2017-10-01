package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.Arrays;

/**
 * class SimpleSet<E> - простой Set на основе массива.
 * @param <E> дженерик.
 */
public class SimpleSet<E> extends DynamicArray<E> {

    /**
     * Конструктор, вызвает дефолтный конструктор DynamicArray<E>.
     */
    public SimpleSet() {
        super();
    }

    /**
     * Добавление объекта в множество.
     * @param value объект.
     * @return true если добавлен, false, если это дубль.
     */
    public boolean add(E value) {
        boolean result = false;
        if (!contains(value)) {
            super.add(value);
            result = true;
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
        if (!binaryContains(value)) {
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
        boolean result = false;
        Arrays.sort(container);
        for (Object obj : container) {
            if (obj != null && obj.equals(value)) {
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
    private boolean binaryContains(E value) {
        Arrays.sort(container);
        boolean result = false;
        int lowerBound = 0;
        int upperBound = container.length - 1;
        int current;
        if (upperBound > 0) {
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
        }
        return result;
    }

}
