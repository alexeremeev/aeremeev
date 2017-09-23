package ru.job4j.generic;

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
        this.objects[index++] = value;
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
     * Удаление объекта из массива по индексу.
     * @param position индекс удаляемого.
     */
    public void remove(int position) {
        System.arraycopy(objects, position + 1, objects, position, index - position - 1);
        objects[--index] = null;
    }

    /**
     * Обновление объекта в заданной ячейке массива.
     * @param position индекс обновляемого объекта.
     * @param value объект, который необходимо записать в ячейку.
     */
    public void update(int position, E value) {
        objects[position] = value;
    }
}
