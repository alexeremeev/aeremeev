package ru.job4j.tictactoe;
/**
 * Printer - interface for printing field and messages to console.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public interface Printer {
    /**
     * Print message to console.
     * @param message message.
     */
    void printMessage(String message);

    /**
     * Print game field to console.
     * @param gameField game field.
     */
    void printGameField(Cell[] gameField);
}
