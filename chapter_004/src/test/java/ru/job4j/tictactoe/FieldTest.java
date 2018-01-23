package ru.job4j.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Field class tests.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1.1
 */
public class FieldTest {
    /**
     * Test of checking horizontal lines.
     */
    @Test
    public void whenCheckHorizontalLineWhenGetCorrectResult() {
        Field field = new Field(3);
        field.fillGameField();
        field.getGameField()[3].setMark(Mark.X);
        field.getGameField()[4].setMark(Mark.X);
        field.getGameField()[5].setMark(Mark.X);

        boolean result = field.checkGameField(Mark.X);

        assertThat(result, is(true));
    }
    /**
     * Test of checking vertical lines.
     */
    @Test
    public void whenCheckVerticalLineWhenGetCorrectResult() {
        Field field = new Field(3);
        field.fillGameField();
        field.getGameField()[1].setMark(Mark.X);
        field.getGameField()[4].setMark(Mark.X);
        field.getGameField()[7].setMark(Mark.X);

        boolean result = field.checkGameField(Mark.X);

        assertThat(result, is(true));
    }
    /**
     * Test of checking diagonal line.
     */
    @Test
    public void whenCheckDiagonalLineWhenGetCorrectResult() {
        Field field = new Field(3);
        field.fillGameField();
        field.getGameField()[2].setMark(Mark.X);
        field.getGameField()[4].setMark(Mark.X);
        field.getGameField()[6].setMark(Mark.X);

        boolean result = field.checkGameField(Mark.X);

        assertThat(result, is(true));
    }
    /**
     * Test of checking backwards diagonal line.
     */
    @Test
    public void whenCheckAnotherDiagonalWhenGetCorrectResult() {
        Field field = new Field(3);
        field.fillGameField();
        field.getGameField()[0].setMark(Mark.X);
        field.getGameField()[4].setMark(Mark.X);
        field.getGameField()[8].setMark(Mark.X);

        boolean result = field.checkGameField(Mark.X);

        assertThat(result, is(true));
    }

    /**
     * Test of AIO checking method.
     */
    @Test
    public void whenCheckViaAIOMethodWhenGetCorrectResult() {
        Field field = new Field(3);
        field.fillGameField();
        field.getGameField()[0].setMark(Mark.X);
        field.getGameField()[3].setMark(Mark.X);
        field.getGameField()[6].setMark(Mark.X);
        field.getGameField()[2].setMark(Mark.O);

        boolean result = field.checkGameField(Mark.X);

        assertThat(result, is(true));
    }



}