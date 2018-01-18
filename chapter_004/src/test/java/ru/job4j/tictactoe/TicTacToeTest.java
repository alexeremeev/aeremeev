package ru.job4j.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TicTacToe test.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class TicTacToeTest {
    /**
     * Test of gameplay via stub inputs.
     */
    @Test
    public void whenPlayTicTacToeViaStubsWhenGetCorrectWinCount() {
        Field field = new Field(3);
        ConsolePrinter printer = new ConsolePrinter();
        String[] firstPlayerMoves = new String[] {"1", "4", "7", "7", "8", "1", "2", "3"};
        String[] secondPlayerMoves = new String[] {"2", "5", "5", "1", "9", "5", "4"};
        AbstractPlayer first = new StubPlayer("Dummy-1", Mark.X, field, firstPlayerMoves);
        AbstractPlayer second = new StubPlayer("Dummy-2", Mark.O, field, secondPlayerMoves);
        Game ttt = new TicTacToe(first, second, field, printer);
        ttt.playMultipleRounds(3);

        int firstResult = first.getWinCount();
        int secondResult = second.getWinCount();

        assertThat(firstResult, is(2));
        assertThat(secondResult, is(1));
    }
}