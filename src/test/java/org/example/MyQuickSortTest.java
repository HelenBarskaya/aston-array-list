package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MyQuickSortTest {

    @DisplayName("Quick sort test")
    @Test
    public void quickSortTest() {
        List<String> list = List.of("z", "d", "b", "p", "i", "c", "a");

        MyArrayList<String> myList = new MyArrayList<>(list);
        MyQuickSort.sort(myList, String::compareTo);

        Assertions.assertArrayEquals(new String[]{"a", "b", "c", "d", "i", "p", "z"}, myList.toArray());
    }
}
