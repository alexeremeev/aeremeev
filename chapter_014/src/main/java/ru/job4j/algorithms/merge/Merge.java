package ru.job4j.algorithms.merge;

import java.util.ArrayList;
import java.util.List;

public class Merge<T extends Comparable<? super T>> {

    public void mergeSort(List<T> list) {
        List<T> workList = new ArrayList<>();
        recursiveMerge(workList, list, 0, list.size() - 1);
    }

    private void recursiveMerge(List<T> workList, List<T> original, int lower, int upper) {
        if (lower == upper) {
            return;
        } else {
            int middle = (lower + upper) / 2;
            recursiveMerge(workList, original, lower, middle);
            recursiveMerge(workList, original, middle + 1, upper);
            merge(workList, original, lower, middle + 1, upper);
        }
    }

    public void iterativeMergeSort(List<T> original) {
        List<T> workList = new ArrayList<>();
        for (int i = 1; i <= original.size() / 2 + 1; i *= 2) {
            for (int j = i; j < original.size(); j += 2 * i) {
                this.merge(workList, original, j - i,  j, Math.min(j + i, original.size() - 1));
            }
        }
    }


    private void merge(List<T> workList, List<T> original, int lowerPart, int highPart, int upper) {
        int index = 0;
        int lower = lowerPart;
        int middle = highPart - 1;
        int n = upper - lower + 1;
        while (lowerPart <= middle && highPart <= upper) {
            if (original.get(lowerPart).compareTo(original.get(highPart)) < 0) {
                workList.add(index++, original.get(lowerPart++));
            } else {
                workList.add(index++, original.get(highPart++));
            }
        }
        while (lowerPart <= middle) {
            workList.add(index++, original.get(lowerPart++));
        }
        while (highPart <= upper) {
            workList.add(index++, original.get(highPart++));
        }
        for (index = 0; index < n; index++) {
            original.set(lower + index, workList.get(index));
        }
    }
}
