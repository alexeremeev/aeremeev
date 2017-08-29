package ru.job4j.array;
/**
 * Class RotateArray - поворот двумерного массива по часовой стрелке на 90 градусов.
 * @author aeremeev.
 * @since 29.08.2017.
 * @version 1.
 */
public class RotateArray {
    /**
     * Метод поворота двумерного массива по часовой стрелке на 90 градусов.
     * @param array входной массив.
     * @return повернутый на 90 градусов по часовой стрелке массив.
     */
    public int[][] rotate(int[][] array) {
        int size = array.length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = i; j < size - 1 - i; j++) {
                int swap = array[i][j];
                array[i][j] = array[size - 1 - j][i];
                array[size - 1 - j][i] = array[size - 1 - i][size - 1 - j];
                array[size - 1 - i][size - 1 - j] = array[j][size - 1 - i];
                array[j][size - 1 - i] = swap;
            }
        }
        return array;
    }
}