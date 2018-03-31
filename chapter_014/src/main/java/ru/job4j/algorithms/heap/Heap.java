package ru.job4j.algorithms.heap;

import java.util.List;

public class Heap<T extends Comparable<? super T>> {

    public void pyramidSort(List<T> list) {
        for (int index = list.size() / 2 - 1; index >= 0; index--) {
            buildHeap(list, list.size(), index);
        }
        for (int index = list.size() - 1; index >= 0; index--) {
            this.swap(0, index, list);
            buildHeap(list, index, 0);
        }
    }

    private void buildHeap(List<T> list, int heapSize, int index) {
        int max = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        if (left < heapSize && list.get(left).compareTo(list.get(max)) > 0) {
            max = left;
        }
        if (right < heapSize && list.get(right).compareTo(list.get(max)) > 0) {
            max = right;
        }
        if (max != index) {
            this.swap(index, max, list);
            buildHeap(list, heapSize, max);
        }
    }

    private void swap(int firstIndex, int secondIndex, List<T> list) {
        T swap = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, swap);
    }

}
