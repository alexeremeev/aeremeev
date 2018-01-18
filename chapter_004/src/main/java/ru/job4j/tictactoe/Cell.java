package ru.job4j.tictactoe;
/**
 * Cell - model for cell in tic tac toe field.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */
public class Cell {
    /**
     * Cell index.
     */
    private int index;
    /**
     * Cell mark.
     */
    private Mark mark;

    /**
     * Constructor.
     * @param index cell index.
     * @param mark cell mark.
     */
    public Cell(int index, Mark mark) {
        this.index = index;
        this.mark = mark;
    }

    /**
     * Mark setter.
     * @param mark mark.
     */
    public void setMark(Mark mark) {
        this.mark = mark;
    }

    /**
     * Index getter.
     * @return index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Mark getter.
     * @return mark.
     */
    public Mark getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return String.format("%d %s", this.index, this.mark);
    }
}
