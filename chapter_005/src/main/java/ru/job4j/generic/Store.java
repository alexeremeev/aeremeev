package ru.job4j.generic;

/**
 * abstract class Store - хранилище, реализованное на основе SimpleArray<T>.
 * @param <T> дженерики, расширающие класс Base.
 */
public abstract class Store<T extends Base> {

    /**
     * Для хранения элементов используется SimpleArray<T>.
     */
    private SimpleArray<T> storeArray;

    /**
     * Конструктор.
     * @param size размемр storeArray.
     */
    protected Store(int size) {
        this.storeArray = new SimpleArray<>(size);
    }

    /**
     * Добавление нового элемента в хранилище.
     * @param value элемент.
     */
    public void add(T value) {
        storeArray.add(value);
    }

    /**
     * Обновление данных элемента в хранлилище.
     * @param value элемент.
     */
    public void update(T value) {
        storeArray.update(storeArray.getIndexOfObject(value), value);
    }

    /**
     * Удаление элемента из хранилища.
     * @param value элемент.
     */
    public void remove(T value) {
        storeArray.remove(storeArray.getIndexOfObject(value));
    }

    /**
     * Получение элемента по индексу в хранилище.
     * @param index индекс.
     * @return элемент.
     */
    public T get(int index) {
        return storeArray.get(index);
    }

}
