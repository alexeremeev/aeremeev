package ru.job4j.tictactoe;

import java.util.Collections;
/**
 * Console Printer.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class ConsolePrinter implements Printer {
    /**
     * Prints message to console.
     * @param message message.
     */
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints formatted game field to console
     * @param gameField game field.
     */
    @Override
    public void printGameField(Cell[] gameField) {
        int size = (int) Math.sqrt(gameField.length);
        int index = 0;
        String border = String.join("", Collections.nCopies(size, "----+"));
        for (int i = 0; i != size; i++) {
            System.out.print("+");
            System.out.println(border);
            System.out.print("|");
            for (int j = 0; j != size; j++) {
                if (gameField[index].getMark() != Mark.EMPTY) {
                    System.out.print(String.format(" %-2s |", gameField[index++].getMark()));
                } else {
                    System.out.print(String.format(" %-2s |", gameField[index++].getIndex()));
                }
            }
            System.out.println();
        }
        System.out.print("+");
        System.out.println(border);
    }

}
