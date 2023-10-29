package org.example;

public class MyQuickSort {

    public static <E extends Comparable<? super E>> void sort(MyArrayList<E> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static <E extends Comparable<? super E>> void quickSort(MyArrayList<E> list, int left, int right) {
        if (right - left <= 0) {
            return;
        }

        E target = list.get(right);
        int partition = partition(list, target, left, right);

        quickSort(list, left, partition - 1);
        quickSort(list, partition + 1, right);
    }

    private static <E extends Comparable<? super E>> int partition(MyArrayList<E> list, E target, int left, int right) {
        int leftPointer = left;
        int rightPointer = right - 1;

        while (true) {
            while (list.get(leftPointer).compareTo(target) < 0) {
                ++leftPointer;
            }
            while (list.get(rightPointer).compareTo(target) > 0) {
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

    private static <E extends Comparable<? super E>> void swap(MyArrayList<E> list, int a, int b) {
        E buf = list.get(a);
        list.set(a, list.get(b));
        list.set(b, buf);
    }
}
