package ru.job4j.tictactoe;

/**
 * Stub Player for JUnit tests.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class StubPlayer extends AbstractPlayer {
    /**
     * Moves array.
     */
    private String[] moves;
    /**
     * Position index.
     */
    private int position = 0;

    /**
     * Constructor.
     * @param name player's name.
     * @param mark player's mark (X | O).
     * @param field game field.
     * @param moves moves array.
     */
    public StubPlayer(String name, Mark mark, Field field, String[] moves) {
        super(name, mark, field);
        this.moves = moves;
    }

    /**
     * Get move from array.
     * @return next move from array.
     */
    @Override
    public int move() {
        return Integer.valueOf(moves[position++]) - 1;
    }
}
