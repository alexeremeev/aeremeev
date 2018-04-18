package ru.job4j.structures.arrays;

public class Nullify {

    public void nullifyArray(int[][] array) {
        boolean[][] nullified = new boolean[array.length][array[0].length];
        for (int i = 0; i != array.length; i++) {
            for (int j = 0; j != array[i].length; j++) {
                if (array[i][j] == 0 && !nullified[i][j]) {
                    this.nullify(i, j, array, nullified);
                }
            }
        }
    }

    public void nullify(int row, int col, int[][] original, boolean[][] nullified) {
        for (int i = 0; i != original.length; i++) {
            if (original[row][i] != 0 && !nullified[row][i]) {
                original[row][i] = 0;
                nullified[row][i] = true;
            }
        }
        for (int i = 0; i != original[col].length; i++) {
            if (original[i][col] != 0 && !nullified[i][col]) {
                original[i][col] = 0;
                nullified[i][col] = true;
            }
        }
    }
}
