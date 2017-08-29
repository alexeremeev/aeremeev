package ru.job4j.array;
/**
 * Class Turn - переворот массива.
 * @author aeremeev.
 * @since 29.08.2017.
 * @version 1.
 */
public class Turn {
    /**
     * Метод перестановки начальных и конечных значений массива.
     * @param array входной массив.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        int swap;
        for (int i = 0; i < array.length / 2; i++) {
            swap = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = swap;
        }
        return array;
    }
}
