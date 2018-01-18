package ru.job4j.tictactoe;

import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Human class test.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class HumanTest {

    /**
     * Test of correct input of cell index.
     */
    @Test
    public void whenUseCorrectInputThenGetCorrectMove() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Field field = new Field(3);
        field.fillGameField();
        AbstractPlayer player = new Human("Test Human", Mark.X, field);

        int result = player.move();

        assertThat(result, is(0));

    }

    /**
     * Test of incorrect input of cell index.
     */
    @Test
    public void whenUseIncorrectInputTheGetMinusOne() {
        String input = "10";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Field field = new Field(3);
        field.fillGameField();
        AbstractPlayer player = new Human("Test Human", Mark.X, field);

        int result = player.move();

        assertThat(result, is(-1));
    }

    /**
     * Test of not numeric input.
     */
    @Test
    public void whenInputNotNumberThenValidateFalse() {
        String input = "Random input";
        Field field = new Field(3);
        field.fillGameField();
        Human player = new Human("Test Human", Mark.X, field);
        boolean result = player.validateNumber(input);

        assertThat(result, is(false));
    }
}