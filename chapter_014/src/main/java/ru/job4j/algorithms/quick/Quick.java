package ru.job4j.algorithms.quick;

import java.util.List;

public class Quick<T extends Comparable<? super T>> {

    public void quickSort(List<T> list) {
        this.recursiveQuickSort(list, 0, list.size() - 1);
    }

    private void recursiveQuickSort(List<T> list, int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            T anchor = list.get(right);
            int partition = this.sliceList(list, left, right, anchor);
            recursiveQuickSort(list, left, partition - 1);
            recursiveQuickSort(list, partition + 1, right);
        }
    }

    private int sliceList(List<T> list, int left, int right, T anchor) {
        int leftPart = left;
        int rightPart = right - 1;
        while (true) {
            while (list.get(leftPart).compareTo(anchor) < 0) {
                leftPart++;
            }
            while (rightPart > 0 && list.get(rightPart).compareTo(anchor) > 0) {
                rightPart--;
            }
            if (leftPart >= rightPart) {
                break;
            } else {
                swap(leftPart, rightPart, list);
            }
        }
        swap(leftPart, right, list);
        return leftPart;
    }

    private void swap(int firstIndex, int secondIndex, List<T> list) {
        T swap = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, swap);
    }
}
