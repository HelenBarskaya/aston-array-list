package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Assertions.assertThrows(NoSuchElementException.class, list.iterator()::next);
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
        String[] strings = new String[]{"One Item", "Two Item", "Three Item"};
        List<String> list = new MyArrayList<>(Arrays.asList(strings));

        Iterator<String> iterator = list.iterator();
        for (String string : strings) {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertEquals(string, iterator.next());
        }
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
    }

    @DisplayName("Get next id from the iterator test")
    @Test
    public void getNextIdTest() {
        List<String> list = new MyArrayList<>();
        list.addAll(Arrays.asList("One Item", "Two Item", "Three Item"));

        ListIterator<String> iterator = list.listIterator();
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(i, iterator.nextIndex());
            iterator.next();
        }
    }

    @DisplayName("Get previous id from the iterator test")
    @Test
    public void getPrevIdTest() {
        List<String> list = new MyArrayList<>();
        list.addAll(Arrays.asList("One Item", "Two Item", "Three Item"));

        ListIterator<String> iterator = list.listIterator();
        for (int i = 1; i < list.size(); i++) {
            iterator.next();
            Assertions.assertEquals(i - 1, iterator.previousIndex());
        }
    }

    @DisplayName("ListIterator on an empty collection test")
    @Test
    public void listIteratorOnEmptyArrayTest() {
        List<String> list = new MyArrayList<>();

        Assertions.assertFalse(list.listIterator().hasNext());
        Assertions.assertThrows(NoSuchElementException.class, list.listIterator()::next);
        Assertions.assertThrows(NoSuchElementException.class, list.listIterator()::previous);
    }

    @DisplayName("ListIterator on a collection with one item test")
    @Test
    public void listIteratorOnOneItemArrayTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");

        Assertions.assertTrue(list.listIterator().hasNext());
        Assertions.assertEquals("One Item", list.listIterator().next());
        Assertions.assertThrows(NoSuchElementException.class, list.listIterator()::previous);
    }

    @DisplayName("Iterator on a collection with many item test")
    @Test
    public void listIteratorOnManyItemArrayTest() {
        String[] strings = new String[]{"One Item", "Two Item", "Three Item"};
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");

        ListIterator<String> iterator = list.listIterator();
        for (String string : strings) {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertEquals(string, iterator.next());
        }
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);

        for (int i = strings.length - 1; i > 0; i--) {
            Assertions.assertTrue(iterator.hasPrevious());
            Assertions.assertEquals(strings[i - 1], iterator.previous());
        }
        Assertions.assertThrows(NoSuchElementException.class, iterator::previous);
    }

    @DisplayName("Delete an item from the list by the iterator test")
    @Test
    public void deleteElementByIteratorTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        Iterator<String> iterator = list.iterator();

        Assertions.assertThrows(NoSuchElementException.class, iterator::remove);

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        Assertions.assertArrayEquals(new String[]{}, list.toArray());
    }

    @DisplayName("Set an item from the list by the iterator test")
    @Test
    public void setElementByIteratorTest() {
        String[] strings = new String[]{"Four Item", "Five Item", "Six Item"};
        int pointer = 0;
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        ListIterator<String> iterator = list.listIterator();

        Assertions.assertThrows(NoSuchElementException.class, () -> iterator.set("Null Item"));

        while (iterator.hasNext()) {
            iterator.next();
            iterator.set(strings[pointer++]);
        }
        Assertions.assertArrayEquals(strings, list.toArray());
    }

    @DisplayName("Add an item to the list by the iterator test")
    @Test
    public void addElementByIteratorTest() {
        String[] strings = new String[]{"Four Item", "Five Item", "Six Item"};
        List<String> list = new MyArrayList<>();
        ListIterator<String> iterator = list.listIterator();

        for (String string : strings) {
            iterator.add(string);
        }
        Assertions.assertArrayEquals(new String[]{"Six Item", "Five Item", "Four Item"}, list.toArray());
    }

    @DisplayName("MyArrayList contains an object test")
    @Test
    public void containsObjectTest() {
        List<String> collection = List.of("Hello", "World!");
        List<String> arrayFromCollection = new MyArrayList<>(collection);

        Assertions.assertFalse(arrayFromCollection.contains("Bye"));
        Assertions.assertTrue(arrayFromCollection.contains("Hello"));
    }

    @DisplayName("Set an element by the index test")
    @Test
    public void setValueByIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");

        Assertions.assertEquals("Three Item", list.set(0, "Three Item"));
        Assertions.assertThrows(NoSuchElementException.class, () -> list.set(3, "Three Item"));
    }

    @DisplayName("Get an element by the index test")
    @Test
    public void getValueByIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");

        Assertions.assertEquals("One Item", list.get(0));
        Assertions.assertThrows(NoSuchElementException.class, () -> list.get(3));
    }

    @DisplayName("Get an index by the element test")
    @Test
    public void getIndexByElementTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Two Item");

        Assertions.assertEquals(0, list.indexOf("One Item"));
        Assertions.assertEquals(1, list.indexOf("Two Item"));
        Assertions.assertEquals(2, list.lastIndexOf("Two Item"));
        Assertions.assertEquals(-1, list.indexOf("Three Item"));
        Assertions.assertEquals(-1, list.lastIndexOf("Three Item"));
    }

    @DisplayName("Add an element by the index test")
    @Test
    public void addElementByIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Three Item");
        list.add(1, "Two Item");

        Assertions.assertArrayEquals(new String[]{"One Item", "Two Item", "Three Item"}, list.toArray());
        Assertions.assertThrows(NoSuchElementException.class, () -> list.add(5, "Six Item"));
    }

    @DisplayName("Remove an element by the index test")
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
    public void removeElementTest() {
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

        //noinspection ConstantValue
        Assertions.assertTrue(list.isEmpty());
        Assertions.assertArrayEquals(new String[]{}, list.toArray());
    }

    @DisplayName("Add all elements from the collection test")
    @Test
    public void addAllElementsFromCollectionTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        List<String> additionalList = List.of("Three Item", "Four Item");

        Assertions.assertTrue(list.addAll(additionalList));
        Assertions.assertFalse(list.addAll(new ArrayList<>()));
        Assertions.assertArrayEquals(new String[]{"One Item", "Two Item", "Three Item", "Four Item"}, list.toArray());
    }

    @DisplayName("Remove all elements that are contained in the specified collection test")
    @Test
    public void removeElementsContainedInCollectionTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        list.add("Three Item");
        list.add("Three Item");
        list.add("Four Item");
        list.add("Five Item");

        List<String> listToRemove = List.of("Three Item", "Four Item");
        Assertions.assertTrue(list.removeAll(listToRemove));
        Assertions.assertArrayEquals(new String[]{"One Item", "Two Item", "Five Item"}, list.toArray());
    }

    @DisplayName("Retains only the elements in this list that are contained in the specified collection test")
    @Test
    public void retainAllElementsFromCollectionTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Three Item");
        list.add("Three Item");
        list.add("Four Item");
        list.add("Five Item");

        List<String> removedList = List.of("Three Item", "Four Item");

        Assertions.assertTrue(list.retainAll(removedList));
        Assertions.assertArrayEquals(new String[]{"Three Item", "Three Item", "Four Item"}, list.toArray());
    }

    @DisplayName("Add all elements from the index test")
    @Test
    public void addElementsFromIndexTest() {
        List<String> list = new MyArrayList<>();
        list.add("One Item");
        list.add("Two Item");
        List<String> additionalList = List.of("Three Item", "Four Item");

        Assertions.assertTrue(list.addAll(1, additionalList));
        Assertions.assertFalse(list.addAll(0, new ArrayList<>()));
        Assertions.assertArrayEquals(new String[]{"One Item", "Three Item", "Four Item", "Two Item"}, list.toArray());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(5, additionalList));
    }

    @DisplayName("Get array from collection")
    @Test
    public void toArrayTest() {
        String[] strings = {"One Item", "Two Item", "Three Item", "Four Item"};
        List<String> defaultList = List.of(strings);
        List<String> list = new MyArrayList<>();
        list.addAll(defaultList);

        Assertions.assertArrayEquals(strings, list.toArray(new String[0]));
        Assertions.assertArrayEquals(strings, list.toArray());
    }
}
