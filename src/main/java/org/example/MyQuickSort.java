package org.example;

import java.util.Comparator;
import java.util.List;

public class MyQuickSort {

    public static <E> void sort(List<E> list, Comparator<E> comp) {
        quickSort(list, 0, list.size() - 1, comp);
    }

    private static <E> void quickSort(List<E> list, int left, int right, Comparator<E> comp) {
        if (right - left <= 0) {
            return;
        }

        E target = list.get(right);
        int partition = partition(list, target, left, right, comp);

        quickSort(list, left, partition - 1, comp);
        quickSort(list, partition + 1, right, comp);
    }

    private static <E> int partition(List<E> list, E target, int left, int right, Comparator<E> comp) {
        int leftPointer = left;
        int rightPointer = right - 1;

        while (true) {
            while (comp.compare(list.get(leftPointer), target) < 0 && leftPointer < right) {
                ++leftPointer;
            }
            while (comp.compare(list.get(rightPointer), target) > 0 && rightPointer >= leftPointer + 1) {
                --rightPointer;
            }
            if (leftPointer >= rightPointer) {
                break;
            } else {
                swap(list, leftPointer, rightPointer);
            }
        }

        swap(list, leftPointer, right);
        return leftPointer;
    }

    private static <E> void swap(List<E> list, int a, int b) {
        E buf = list.get(a);
        list.set(a, list.get(b));
        list.set(b, buf);
    }
}
