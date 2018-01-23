package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Game field for tic tac toe.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1.1
 */
public class Field {
    /**
     * Size of square field. Length of side.
     */
    private final int size;
    /**
     * Array of cells.
     */
    private Cell[] gameField;

    /**
     * Constructor.
     * @param size size of square field. Length of side.
     */
    public Field(int size) {
        this.size = size;
        this.gameField = new Cell[size * size];
    }

    /**
     * Cells array getter.
     * @return cells array.
     */
    public Cell[] getGameField() {
        return this.gameField;
    }

    /**
     * Get list of empty cells.
     * @return list of empty cells.
     */
    public List<Cell> emptyCells() {
        List<Cell> result = new ArrayList<>(this.gameField.length);
        for (int index = 0; index != this.gameField.length; index++) {
            if (this.gameField[index].getMark() == Mark.EMPTY) {
                result.add(this.gameField[index]);
            }
        }
        return result;
    }

    /**
     * Fill game field with empty cells.
     */
    public void fillGameField() {
        for (int index = 0; index != this.gameField.length; index++) {
            this.gameField[index] = new Cell(index + 1, Mark.EMPTY);
        }
    }

    /**
     * Check all types of lines for matching mark.
     * @param mark mark.
     * @return true, if one line is matching with mark.
     */
    public boolean checkGameField(Mark mark) {
        boolean result = false;
        for (int i = 0; i != this.size; i++) {
            result = this.checkLine(i * this.size, this.size - 1 + i * this.size, 1, mark);
            if (result) {
                break;
            }
        }
        if (!result) {
            for (int i = 0; i != this.size; i++) {
                result = this.checkLine(i, i + this.size * (this.size - 1), this.size, mark);
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            int start = 0;
            int end = this.size * this.size - 1;
            int step = this.size + 1;
            for (int i = 0; i != 2; i++) {
                result = this.checkLine(start, end, step, mark);
                if (result) {
                    break;
                }
                start = this.size - 1;
                end = this.size * (this.size - 1);
                step = this.size - 1;
            }
        }
        return result;
    }

    /**
     * /**
     * Check line for matching mark.
     * @param start gameField[start] index.
     * @param end gameField[end] index.
     * @param step step between cells.
     * @param mark mark.
     * @return true, if line have all matching marks.
     */
    public boolean checkLine(int start, int end, int step, Mark mark) {
        boolean result = true;
        int index = start;
        while (index <= end) {
            if (gameField[index].getMark() != mark) {
                result = false;
                break;
            }
            index += step;
        }
        return result;
    }

    /**
     * Validate if player move is correct by checking indexes.
     * @param move player's chosen cell index.
     * @return true, if move is valid.
     */
    public boolean validateMove(int move) {
        return move >= 1 && move <= this.gameField.length && this.gameField[move - 1].getMark() == Mark.EMPTY;
    }
}
