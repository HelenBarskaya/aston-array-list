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
        Assertions.assertArrayEquals(new String[]{}, emptyArray.toArray());
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @DisplayName("Array with capacity initialization test")
    @Test
    public void initWithCapacityTest() {
        List<String> arrayWithCapacity = new MyArrayList<>(5);
        Assertions.assertArrayEquals(new String[]{}, arrayWithCapacity.toArray());
    }

    @DisplayName("Array from collection initialization test")
    @Test
    public void initFromCollectionTest() {
        List<String> collection = List.of("Hello", "World!");
        List<String> arrayFromCollection = new MyArrayList<>(collection);
        Assertions.assertArrayEquals(new String[]{"Hello", "World!"}, arrayFromCollection.toArray());
    }

    @DisplayName("Get size test")
    @Test
    public void getSizeTest() {
        List<String> collection = List.of("Hello", "World!");
        List<String> arrayFromCollection = new MyArrayList<>(collection);
        Assertions.assertEquals(2, arrayFromCollection.size());
    }

    @DisplayName("Check the emptiness test")
    @Test
    public void isEmptyTest() {
        //noinspection MismatchedQueryAndUpdateOfCollection
        List<String> emptyArray = new MyArrayList<>();
        List<String> collection = List.of("Hello", "World!");
        List<String> arrayFromCollection = new MyArrayList<>(collection);
        Assertions.assertFalse(arrayFromCollection.isEmpty());
        Assertions.assertTrue(emptyArray.isEmpty());
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
        List<String> collection = List.of("Hello", "World!");
        List<String> arrayFromCollection = new MyArrayList<>(collection);
        Assertions.assertFalse(arrayFromCollection.contains("Bye"));
        Assertions.assertTrue(arrayFromCollection.contains("Hello"));
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
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Three Item");
        list.add(1, "Two Item");
        Assertions.assertArrayEquals(new String[]{"One Item", "Two Item", "Three Item"}, list.toArray());
        Assertions.assertThrows(NoSuchElementException.class, () -> list.add(5, "Six Item"));
    }

    @DisplayName("Remove element by index test")
    @Test
    public void removeByIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.remove(1);
        Assertions.assertArrayEquals(new String[]{"One Item", "Three Item"}, list.toArray());
        Assertions.assertEquals("Three Item", list.remove(1));
        Assertions.assertThrows(NoSuchElementException.class, () -> list.remove(1));
    }

    @DisplayName("Remove element test")
    @Test
    public void removeTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.remove("Two Item");
        Assertions.assertArrayEquals(new String[]{"One Item", "Three Item"}, list.toArray());
        Assertions.assertTrue(list.remove("Three Item"));
        Assertions.assertFalse(list.remove("Three Item"));
    }

    @DisplayName("Get sublist test")
    @Test
    public void subListTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.add("Four Item");
        Assertions.assertArrayEquals(new String[]{"Two Item", "Three Item"}, list.subList(1, 3).toArray());
    }

    @DisplayName("Clear list test")
    @Test
    public void clearTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.clear();
        Assertions.assertEquals(0, list.size());
        Assertions.assertArrayEquals(new String[]{}, list.toArray());
    }

    @DisplayName("Add all elements from collection test")
    @Test
    public void addAllElementsFromCollectionTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        List<String> additionalList = List.of("Three Item", "Four Item");
        Assertions.assertTrue(list.addAll(additionalList));
        Assertions.assertFalse(list.addAll(new ArrayList<>()));
        Assertions.assertArrayEquals(new String[]{"One Item", "Two Item", "Three Item", "Four Item"},
                list.toArray());
    }

    @DisplayName("Add elements from index test")
    @Test
    public void addElementsFromIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        List<String> additionalList = List.of("Three Item", "Four Item");
        Assertions.assertTrue(list.addAll(1, additionalList));
        Assertions.assertFalse(list.addAll(0, new ArrayList<>()));
        Assertions.assertArrayEquals(new String[]{"One Item", "Two Item", "Four Item"},
                list.toArray());
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> list.addAll(5, additionalList));
    }

    @DisplayName("Quick sort test")
    @Test
    public void quickSortTest() {
        List<String> list = List.of("z", "d", "b", "p", "i", "c", "a");
        MyArrayList<String> myList = new MyArrayList<>(list);
        StringComparator comparator = new StringComparator();
        MyQuickSort.sort(myList, comparator);
        Assertions.assertArrayEquals(new String[]{"a", "b", "c", "d", "i", "p", "z"}, myList.toArray());
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
