package ru.job4j.control;
/**
 * Class SortedArrayMerge - слияние двух остортированных массивов в результирующий.
 * @author aeremeev.
 * @since 01.09.2017.
 * @version 1.
 */
public class SortedArrayMerge {
    /**
     * Сливаем два сортированных массива в третий.
     * @param firstArray первый массив.
     * @param secondArray второй массив.
     * @return финальный сортированный массив.
     */
    public int[] merge(int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (firstArray[firstIndex] < secondArray[secondIndex]) {
                result[i] = firstArray[firstIndex];
                firstIndex++;
            } else {
                result[i] = secondArray[secondIndex];
                secondIndex++;
            }
            if (firstIndex == firstArray.length) { // если закончился первый массив, забиваем result остатками второго
                System.arraycopy(secondArray, secondIndex, result, ++i, secondArray.length - secondIndex);
                break;
            }
            if (secondIndex == secondArray.length) { // если закончился второй массив, забиваем result остатками первого
                System.arraycopy(firstArray, firstIndex, result, ++i, firstArray.length - firstIndex);
                break;
            }
        }
        return result;
    }
}
