package ru.job4j.convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }

    /**
     * Конвертация списка в двумерный массив.
     * @param list входной список.
     * @param rows колличество рядов, размерность матрицы.
     * @return двумерная матрица.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[] counter = {0};
        return list.stream().collect(() -> new int[rows][rows],
                (a, i) -> a[counter[0] / rows][counter[0]++ % rows] = i, (a, i) -> {});
    }

    /**
     * Конвертация списка массивов в список Interger.
      * @param list входной список массивов.
     * @return список Integer.
     */
    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
