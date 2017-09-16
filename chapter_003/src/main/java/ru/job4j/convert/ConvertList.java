package ru.job4j.convert;

import java.util.ArrayList;
import java.util.List;

/**
 * class ConvertList - конвертация массива в список и обратно.
 */
public class ConvertList {
    /**
     * Конвертация двумерного массива в список.
     * @param array входной массив.
     * @return список.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> listFromArray = new ArrayList<>();

        for (int i = 0; i != array.length; i++) {
            for (int j = 0; j != array[i].length; j++) {
                listFromArray.add(array[i][j]);
            }
        }
        return listFromArray;
    }

    /**
     * Конвертация списка в двумерный массив.
     * @param list входной список.
     * @param rows колличество рядов, размерность матрицы.
     * @return двумерная матрица.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array = new int[rows][rows];
        for (int i = 0; i != array.length; i++) {
            for (int j = 0; j != array[i].length; j++) {
                if (rows * i + j == list.size()) {
                    break;
                }
                array[i][j] = list.get(rows * i + j);

            }
        }
        return array;
    }

    /**
     * Конвертация списка массивов в список Interger.
      * @param list входной список массивов.
     * @return список Integer.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int index = 0; index != array.length; index++) {
                result.add(array[index]);
            }
        }
        return result;
    }

}
