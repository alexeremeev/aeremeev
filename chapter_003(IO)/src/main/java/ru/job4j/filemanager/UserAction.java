package ru.job4j.filemanager;

/**
 * interface UserAction - действие пользователя.
 */
public interface UserAction {
    /**
     * Исполнить командую
     * @param manager manager.
     * @param select строка ввода.
     */
    void execute(Manager manager, String select);

    /**
     * Описание дествия.
     * @return описание дествия.
     */
    String info();
}
