package ru.job4j.tictactoe;
/**
 * Player interface.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public interface Player {
    /**
     * Make move on field.
     * @return cell index.
     */
    int move();

    /**
     * Check if player win game.
     * @return true, if game is already won.
     */
    boolean isWinner();
}
