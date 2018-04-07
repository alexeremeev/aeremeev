package ru.job4j.algorithms.binary;

import java.util.*;

public class BinarySearch<T extends Comparable<? super T>> {

    public boolean isSort(List<T> list) {
        boolean result = true;
        for (int i = 1; i != list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Find index of value in sorted list using binary search.
     * @param value value, which index needs to be found.
     * @param list list of values.
     * @return index of value in list, or -1 if not found.
     */
    public int findIndex(T value, List<T> list) {
        int result = -1;
        if (this.isSort(list)) {
            int lowerBound = 0;
            int upperBound = list.size() - 1;
            int current;
            if (upperBound > 0) {
                while (true) {
                    current = (lowerBound + upperBound) / 2;
                    if (list.get(current).equals(value)) {
                        result = current;
                        break;
                    } else if (lowerBound > upperBound) {
                        break;
                    } else {
                        T currentValue = list.get(current);
                        int comp = currentValue.compareTo(value);
                        if (comp < 0) {
                            lowerBound = current + 1;
                        } else {
                            upperBound = current - 1;
                        }
                    }
                }
            }
        }
        return result;
    }
}
