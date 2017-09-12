package ru.job4j.chess;

/**
 * Исключение, если фигура не может так ходить по правилам.
 */
public class ImpossibleMoveException extends RuntimeException {

    /**
     * Конструктор исключения.
     * @param message сообщение для пользователя.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
