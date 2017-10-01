package ru.job4j.list;

/**
 * Интерфейс SimpleContainer интерфейс для простых реализаций списков.
 * @param <E> дженерик.
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Метод добавления элемента список.
     * @param e добавляемый элемент.
     * @return true при добавлении элемента.
     */
    boolean add(E e);

    /**
     * Получение элемента из списка по индексу.
     * @param index индекс элемента.
     * @return элемент.
     */
    E get(int index);
}

