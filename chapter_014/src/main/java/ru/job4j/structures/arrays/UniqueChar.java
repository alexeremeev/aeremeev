package ru.job4j.structures.arrays;

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
}
