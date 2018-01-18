package ru.job4j.tictactoe;

/**
 * Abstract Player.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public abstract class AbstractPlayer implements Player {
    /**
     * Player's name.
     */
    private String name;
    /**
     * Player's mark (X | O).
     */
    private Mark mark;
    /**
     * Win counter.
     */
    private int winCount;
    /**
     * Game field.
     */
    private final Field field;

    /**
     * Constructor.
     * @param name player's name.
     * @param mark player's mark (X | O).
     * @param field game field.
     */
    public AbstractPlayer(String name, Mark mark, Field field) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "Player";
        }
        this.mark = mark;
        this.field = field;
        this.winCount = 0;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Win counter getter.
     * @return win counter.
     */
    public int getWinCount() {
        return this.winCount;
    }

    /**
     * Increases win counter.
     */
    public void increaseWinCount() {
        this.winCount += 1;
    }

    /**
     * Game field getter.
     * @return game field.
     */
    public Field getField() {
        return this.field;
    }

    /**
     * Mark getter.
     * @return mark.
     */
    public Mark getMark() {
        return this.mark;
    }

    /**
     * Make move on field.
     * @return cell index.
     */
    @Override
    public abstract int move();

    /**
     * Check if player win game.
     * @return true, if game is already won.
     */
    @Override
    public boolean isWinner() {
        return this.field.checkGameField(this.mark);
    }
}
