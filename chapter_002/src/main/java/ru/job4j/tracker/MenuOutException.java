package ru.job4j.tracker;

/**
 * Исключение выпадания за пределы меню.
 */
public class MenuOutException extends RuntimeException {
    /**
     * Конструктор исключения.
     * @param message сообщение для пользователя.
     */
    public MenuOutException(String message) {
        super(message);
    }
}
