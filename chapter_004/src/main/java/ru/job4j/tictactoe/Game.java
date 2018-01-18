package ru.job4j.tictactoe;

/**
 * Game interface.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public interface Game {
    /**
     * Initialize game.
     */
    void init();

    /**
     * Play one round.
     */
    void playRound();

    /**
     * Play multiple rounds.
     * @param rounds rounds count.
     */
    void playMultipleRounds(int rounds);
}
