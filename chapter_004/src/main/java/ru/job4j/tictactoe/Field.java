package ru.job4j.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Game field for tic tac toe.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
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
        return this.checkHorizontal(mark) || this.checkVertical(mark) || this.checkDiagonal(mark);
    }

    /**
     * Check horizontal lines for matching mark.
     * @param mark mark.
     * @return true, if one line is matching with mark.
     */
    public boolean checkHorizontal(Mark mark) {
        boolean result = true;
        int start = 0;
        for (int i = 0; i != this.size; i++) {
            result = true;
            for (int j = 0; j != this.size; j++) {
                if (gameField[start + j].getMark() != mark) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
            start += this.size;
        }
        return result;
    }
    /**
     * Check vertical lines for matching mark.
     * @param mark mark.
     * @return true, if one line is matching with mark.
     */
    public boolean checkVertical(Mark mark) {
        boolean result = true;
        int start = 0;
        for (int i = 0; i != this.size; i++) {
            result = true;
            for (int j = 0; j != this.size; j++) {
                if (gameField[start + j * size].getMark() != mark) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
            start += 1;
        }
        return result;
    }
    /**
     * Check two diagonal lines for matching mark.
     * @param mark mark.
     * @return true, if one line is matching with mark.
     */
    public boolean checkDiagonal(Mark mark) {
        boolean result = true;
        int start = 0;
        int step = size + 1;
        for (int i = 0; i != 2; i++) {
            result = true;
            for (int j = 0; j != this.size; j++) {
                if (gameField[start + j * step].getMark() != mark) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
            start = size - 1;
            step = size - 1;

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
