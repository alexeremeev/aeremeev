package ru.job4j.generic;

/**
 * abstract class AbstractStore - хранилище, реализованное на основе SimpleArray<Base>.
 * Импелементирует интерфейс Store.
 */
public abstract class AbstractStore implements Store {

    /**
     * Для хранения элементов используется SimpleArray<T>.
     */
    private SimpleArray<Base> storeArray;

    /**
     * Конструктор.
     * @param size размемр storeArray.
     */

    protected AbstractStore(int size) {
        this.storeArray = new SimpleArray<>(size);
    }

    /**
     * Добавление нового элемента в хранилище.
     * @param value элемент.
     */
    public void add(Base value) {
        storeArray.add(value);
    }

    /**
     * Обновление данных элемента в хранлилище.
     * @param value элемент.
     */

    public void update(Base value) {
        storeArray.update(storeArray.getIndexOfObject(value), value);
    }

    /**
     * Удаление элемента из хранилища.
     * @param value элемент.
     */
    public void remove(Base value) {
        storeArray.remove(storeArray.getIndexOfObject(value));
    }

    /**
     * Получение элемента по индексу в хранилище.
     * @param index индекс.
     * @return элемент.
     */
    public Base get(int index) {
        return storeArray.get(index);
    }

}
