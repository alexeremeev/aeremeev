package ru.job4j.generic;

/**
 * Интерфейс хранилища Store.
 * @param <T> дженерик.
 */
public interface Store<T extends Base> {
    /**
     * Добавить элемент в хранилище.
     * @param value элемент.
     */
    void add(T value);

    /**
     * Обновить элемент в хранилище.
     * @param value элемент.
     */
    void update(T value);

    /**
     * Удалить элемент из хранилища.
     * @param value элемент.
     */
    void remove(T value);

    /**
     * Получить элемент по индексу из хранилища.
     * @param index индекс.
     * @return элемент.
     */
    T get(int index);
}
