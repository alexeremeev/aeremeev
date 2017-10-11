//CHECKSTYLE.OFF
package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class DynamicArray - простая реализиация динамического массива.
 * @param <E> дженерик.
 */
@ThreadSafe
public class DynamicArray<E> implements SimpleContainer<E> {
    /**
     * Массив для хранения элементов.
     */
    protected Object[] container;
    /**
     * Индекс последнего элемента в массиве.
     */
    protected int index = 0;

    /**
     * Дефолтный конструктор. Создается массив на 1 элемент.
     */
    public DynamicArray() {
        this.container = new Object[1];
    }

    /**
     * Конструктор.
     * @param size размер массива.
     */
    public DynamicArray(int size) {
        this.container = new Object[size];
    }

    /**
     * Добавление элемента в массив.
     * @param value элемент.
     */
    @GuardedBy("this")
    public synchronized boolean add(E value) {
        if (index < container.length) {
            container[index++] = value;
        } else {
            int newCapacity = container.length + 1;
            container = Arrays.copyOf(container, newCapacity);
            container[index++] = value;
        }
        return true;
    }

    /**
     * Получение элемента из массива по индексу.
     * @param index индекс элемента.
     * @return элемент.
     */
    @GuardedBy("this")
    public synchronized E get(int index) {
        if (index < container.length) {
            return (E) container[index];
        } else {
            throw new NoSuchElementException("No such element in Dynamic Array!");
        }
    }

    /**
     * Итератор.
     * @return итератор.
     */
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < container.length && container[currentIndex] != null;
            }

            @Override
            public E next() {
                return (E) container[currentIndex++];
            }
        };
        return it;
    }
}
//CHECKSTYLE.ON