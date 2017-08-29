package ru.job4j.array;

import java.util.Arrays;
/**
 * Class ArrayDuplicate - удаление дубликатов из массива.
 * @author aeremeev.
 * @since 29.08.2017.
 * @version 1.
 */
public class ArrayDuplicate {
    /**
     * Метод удаления дубликатов из массива использованием Arrays.copyOf.
     * @param array входной массив.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int duplicateCount = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length - duplicateCount; j++) {
                if (array[i].equals(array[j])) {
                    while (j != (array.length - duplicateCount - 1) && array[array.length - duplicateCount - 1].equals(array[j])) {
                        duplicateCount++;
                    }
                    String swap = array[array.length - duplicateCount - 1];
                    array[array.length - duplicateCount - 1] = array[j];
                    array[j] = swap;
                    duplicateCount++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - duplicateCount);
    }
}