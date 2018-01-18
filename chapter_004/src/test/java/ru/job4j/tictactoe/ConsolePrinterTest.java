package ru.job4j.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Console printer test.
 */
public class ConsolePrinterTest {

    /**
     * Simple print of field.
     */
    @Test
    public void printField() {
        Field field = new Field(5);
        field.fillGameField();
        field.getGameField()[0].setMark(Mark.X);
        field.getGameField()[7].setMark(Mark.O);
        field.getGameField()[11].setMark(Mark.O);
        field.getGameField()[19].setMark(Mark.X);

        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.printGameField(field.getGameField());
    }

}