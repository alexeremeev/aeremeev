package ru.job4j.chess;

/**
 * Исключение, если путь фигуры перекрыт другой.
 */
public class OccupiedWayException extends RuntimeException {

    /**
     * Конструктор исключения.
     * @param message сообщение для пользователя.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
