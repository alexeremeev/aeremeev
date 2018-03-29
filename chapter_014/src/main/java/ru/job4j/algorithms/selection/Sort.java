package ru.job4j.algorithms.selection;

import java.util.List;

public class Sort<T extends Comparable<? super T>> {

    public void selectionSort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(index)) < 0) {
                    index = j;
                }
            }
            if (index != i) {
                swap(index, i, list);
            }
        }
    }

    private void swap(int firstIndex, int secondIndex, List<T> list) {
        T swap = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, swap);
    }
}
