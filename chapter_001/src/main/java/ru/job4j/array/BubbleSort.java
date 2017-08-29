package ru.job4j.array;
/**
 * Class BubbleSort - сортировка массива пузырьком.
 * @author aeremeev.
 * @since 29.08.2017.
 * @version 1.
 */
public class BubbleSort {
    /**
     * Метод сортировки массива пузьрком.
     * @param array входной массив.
     * @return отсортированный по возрастанию значений массив.
     */
    public int[] sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
        return array;
    }
}
