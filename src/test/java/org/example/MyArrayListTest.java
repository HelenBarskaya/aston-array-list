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
    public void iteratorOnEmptyArrayTest() {
        List<String> list = new MyArrayList<>();
        Assertions.assertFalse(list.iterator().hasNext());
        Assertions.assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @DisplayName("Iterator on a collection with one item test")
    @Test
    public void iteratorOnOneItemArrayTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        Assertions.assertTrue(list.iterator().hasNext());
        Assertions.assertEquals("One Item", list.iterator().next());
    }

    @DisplayName("Iterator on a collection with many item test")
    @Test
    public void iteratorOnManyItemArrayTest() {
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
    public void containsObjectTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        Assertions.assertTrue(list.contains("Two Item"));
        Assertions.assertFalse(list.contains("Three Item"));
    }

    @DisplayName("Set element by index test")
    @Test
    public void setValueByIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        Assertions.assertEquals("Three Item", list.set(0, "Three Item"));
        Assertions.assertThrows(NoSuchElementException.class, () -> list.set(3, "Three Item"));
    }

    @DisplayName("Get element by index test")
    @Test
    public void getValueByIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        Assertions.assertEquals("One Item", list.get(0));
        Assertions.assertThrows(NoSuchElementException.class, () -> list.get(3));
    }

    @DisplayName("Get index by element test")
    @Test
    public void getIndexByElementTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Two Item");
        Assertions.assertEquals(0, list.indexOf("One Item"));
        Assertions.assertEquals(-1, list.indexOf("Three Item"));
        Assertions.assertEquals(2, list.lastIndexOf("Two Item"));
        Assertions.assertEquals(-1, list.indexOf("Three Item"));
    }

    @DisplayName("Add element by index test")
    @Test
    public void addElementByIndexTest() {
        List<String> defaultList = List.of("One Item", "Two Item", "Three Item");
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Three Item");
        list.add(1, "Two Item");
        Assertions.assertArrayEquals(defaultList.toArray(), list.toArray());
        Assertions.assertThrows(NoSuchElementException.class, () -> list.add(5, "Six Item"));
    }

    @DisplayName("Remove element by index test")
    @Test
    public void removeByIndexTest() {
        List<String> defaultList = List.of("One Item", "Three Item");
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.remove(1);
        Assertions.assertArrayEquals(defaultList.toArray(), list.toArray());
        Assertions.assertEquals("Three Item", list.remove(1));
        Assertions.assertThrows(NoSuchElementException.class, () -> list.remove(1));
    }

    @DisplayName("Remove element test")
    @Test
    public void removeTest() {
        List<String> defaultList = List.of("One Item", "Three Item");
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.remove("Two Item");
        Assertions.assertArrayEquals(defaultList.toArray(), list.toArray());
        Assertions.assertTrue(list.remove("Three Item"));
        Assertions.assertFalse(list.remove("Three Item"));
    }

    @DisplayName("Get sublist test")
    @Test
    public void subListTest() {
        List<String> defaultList = List.of("One Item", "Two Item", "Three Item", "Four Item");
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.add("Four Item");
        Assertions.assertArrayEquals(defaultList.subList(1, 3).toArray(), list.subList(1, 3).toArray());
    }

    @DisplayName("Clear list test")
    @Test
    public void clearTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.clear();
        Assertions.assertEquals(0, list.size());
        Assertions.assertArrayEquals(new ArrayList<>().toArray(), list.toArray());
    }

//    @DisplayName("Get array from collection")
//    @Test
//    public void toArrayTest() {
//        List<String> defaultList = List.of("One Item", "Two Item", "Three Item", "Four Item");
//        List<String> list = new MyArrayList<>();
//        list.add("One Item");
//        list.add("Two Item");
//        list.add("Three Item");
//        list.add("Four Item");
//        String[] res = new String[4];
//        String[] shortRes = new String[2];
//        Assertions.assertArrayEquals(defaultList.toArray(), list.toArray(res));
//        Assertions.assertArrayEquals(defaultList.toArray(), list.toArray());
//        Assertions.assertArrayEquals(defaultList.toArray(shortRes), list.toArray(shortRes));
//    }
}
