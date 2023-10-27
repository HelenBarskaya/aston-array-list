package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MyArrayListTest {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @DisplayName("Empty array initialization test")
    @Test
    public void emptyArrayInitTest() {
        List<String> emptyArray = new MyArrayList<>();
        List<String> defaultEmptyArray = new ArrayList<>();
        Assertions.assertArrayEquals(defaultEmptyArray.toArray(), emptyArray.toArray());
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @DisplayName("Array with capacity initialization test")
    @Test
    public void initWithCapacityTest() {
        List<String> arrayWithCapacity = new MyArrayList<>(5);
        List<String> defaultArrayWithCapacity = new ArrayList<>(5);
        Assertions.assertArrayEquals(defaultArrayWithCapacity.toArray(), arrayWithCapacity.toArray());
    }

    @DisplayName("Array from collection initialization test")
    @Test
    public void initFromCollectionTest() {
        List<String> collection = List.of("Hello", "World!");
        List<String> arrayFromCollection = new MyArrayList<>(collection);
        Assertions.assertArrayEquals(collection.toArray(), arrayFromCollection.toArray());
    }
}
