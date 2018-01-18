package ru.job4j.tictactoe;

import java.util.Random;

/**
 * Dumb Computer. Plays tic tac toe by randomly choosing empty cell on field.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class DumbComputer extends AbstractPlayer {
    /**
     * Random.
     */
    private final Random random = new Random();

    /**
     * Constructor.
     * @param name player's name.
     * @param mark player's mark (X | O).
     * @param field game field.
     */
    public DumbComputer(String name, Mark mark, Field field) {
        super(name, mark, field);
    }

    /**
     * Randomly chooses empty cell on game field.
     * @return random index of cell on game field.
     */
    @Override
    public int move() {
        int maxMove = super.getField().emptyCells().size();
        return super.getField().emptyCells().get(this.random.nextInt(maxMove)).getIndex() - 1;
    }
}
