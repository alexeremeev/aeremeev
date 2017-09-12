package ru.job4j.chess;

/**
 * Исключение, если в source не найдена фигура.
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Конструктор исключения.
     * @param message сообщение для пользователя.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
