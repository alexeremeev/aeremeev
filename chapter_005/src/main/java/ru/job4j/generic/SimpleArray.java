package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * class SimpleArray - простая обертка над массивом, инициализация по размеру.
 * @param <E> дженерик.
 */
public class SimpleArray<E> {
    /**
     * Массив объектов.
     */
    private Object[] objects;
    /**
     * Индекс объекта в массиве.
     */
    private int index = 0;

    /**
     * Конструктор.
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Добавление объекта в массив.
     * @param value добавляемы объект.
     */
    public void add(E value) {
        if (index < this.objects.length) {
            this.objects[index++] = value;
        } else {
            System.out.println("SimpleArray is full, please delete something.");
        }

    }

    /**
     * Получение объекта из массива по индексу.
     * @param position индекс объекта.
     * @return объект с этим индексом.
     */
    public E get(int position) {
        return (E) this.objects[position];
    }

    /**
     * Получение индекса объекта в массиве.
     * @param value объект.
     * @return индекс объекта в массиве.
     */
    public int getIndexOfObject(E value) {
        int pos = -1;
        for (int i = 0; i != objects.length; i++) {
            if (objects[i].equals(value)) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            return pos;
        } else {
            throw new NoSuchElementException("No such element if SimpleArray");
        }
    }

    /**
     * Удаление объекта из массива по индексу.
     * @param position индекс удаляемого.
     */
    public void remove(int position) {
        if (position < this.objects.length) {
            System.arraycopy(objects, position + 1, objects, position, index - position - 1);
            objects[--index] = null;
        }
    }

    /**
     * Обновление объекта в заданной ячейке массива.
     * @param position индекс обновляемого объекта.
     * @param value объект, который необходимо записать в ячейку.
     */
    public void update(int position, E value) {
        if (position < this.objects.length) {
            objects[position] = value;
        }
    }
}
