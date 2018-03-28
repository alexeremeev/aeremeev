package ru.job4j.algorithms.shuffle;

import java.util.List;

public class Sort<T extends Comparable<? super T>> {

    public void shuffleSort(List<T> list) {
        for (int i = list.size() - 1; i > 0; i--){
            for (int j = 0; j < i; j++) {
                if ((list.get(j).compareTo(list.get(j + 1)) > 0)) {
                    this.swap(j, j + 1, list);
                }
            }
        }
    }

    public void shakerSort(List<T> list) {
        int left = 0;
        int right = list.size() - 1;
        do {
            for (int i = left; i < right; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    this.swap(i, i + 1, list);
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (list.get(i).compareTo(list.get(i - 1)) < 0) {
                    this.swap(i, i - 1, list);
                }
            }
            left++;
        } while (left < right);
    }

    private void swap(int firstIndex, int secondIndex, List<T> list) {
        T swap = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, swap);
    }
}
