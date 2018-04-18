package ru.job4j.structures.arrays;

import java.util.Arrays;

public class UniqueChar {

    public boolean isUnique(char[] sequence) {
        boolean result = true;
        int index = 0;
        while (result && index != sequence.length) {
            for (int i = index; i != sequence.length; i++) {
                if (i != index && sequence[i] == sequence[index]) {
                    result = false;
                    break;
                }
            }
            index++;
        }
        return result;
    }

    boolean isUniqueSort(char[] sequence) {
        Arrays.sort(sequence);
        boolean result = true;

        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] == sequence[i - 1]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isUniqueByteCheck(char[] sequence) {
        int checker = 0;
        boolean result = true;

        for (Character letter : sequence) {
            int bitAtIndex = letter - 'a';
            if ((checker & (1 << bitAtIndex)) > 0) {
                result = false;
                break;
            }
            checker = checker | (1 << bitAtIndex);
        }
        return result;
    }
}
