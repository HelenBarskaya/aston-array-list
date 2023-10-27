package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @DisplayName("Iterator on an empty collection test")
    @Test
    public void iteratorOnEmptyArrayTest(){
        List<String> list = new MyArrayList<>();
        Assertions.assertFalse(list.iterator().hasNext());
        Assertions.assertThrows(NoSuchElementException.class,()->list.iterator().next());
    }

    @DisplayName("Iterator on a collection with one item test")
    @Test
    public void iteratorOnOneItemArrayTest(){
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        Assertions.assertTrue(list.iterator().hasNext());
        Assertions.assertEquals("One Item", list.iterator().next());
    }

    @DisplayName("Iterator on a collection with many item test")
    @Test
    public void iteratorOnManyItemArrayTest(){
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        Iterator<String> iterator = list.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("One Item", iterator.next());
        Assertions.assertEquals("Two Item", iterator.next());
    }

    @DisplayName("MyArrayList contains object test")
    @Test
    public void containsObjectTest(){
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        Assertions.assertTrue(list.contains("Two Item"));
        Assertions.assertFalse(list.contains("Three Item"));
    }
}
