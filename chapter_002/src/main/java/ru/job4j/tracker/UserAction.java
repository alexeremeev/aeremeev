package ru.job4j.tracker;

/**
 * Интерфейс действий пользователя.
 */
public interface UserAction {
    /**
     * Ключ меню выбора.
     * @return номер меню.
     */
    int key();

    /**
     * Исполнение действия меню.
     * @param input интерфейс ввода.
     * @param tracker трекер.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Описание пункта меню.
     * @return описание пункта меню.
     */
    String info();

}
