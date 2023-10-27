package org.example;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    //Емкость по умолчанию
    private static final int DEFAULT_CAPACITY = 10;

    //Пустой массив для конструктора по умолчанию
    private static final Object[] EMPTY_ARRAY = {};

    //Массив данных
    private Object[] elementData;

    //Количество непустых элементов в массиве
    private int size = 0;


    //Конструктор по умолчанию, задает пустой массив емкостью 10
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    //Конструктор задает пустой массив емкостью capacity
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal size: " + capacity);
        }
    }

    //Конструктор создающий массив из коллекции
    public MyArrayList(Collection<? extends E> collection) {
        if (!collection.isEmpty()) {
            this.size = collection.size();
            elementData = Arrays.copyOf(collection.toArray(), size, Object[].class);
        } else {
            elementData = EMPTY_ARRAY;
        }
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, (elementData.length * 3) / 2 + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO: 27.10.2023 implement
        return null;
    }

    @Override
    public boolean add(E e) {
        int oldSize = size;
        if (size == elementData.length) {
            grow();
        }
        elementData[oldSize] = e;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public void clear() {
        // TODO: 27.10.2023 implement
    }

    @Override
    public E get(int index) {
        // TODO: 27.10.2023 implement
        return null;
    }

    @Override
    public E set(int index, E element) {
        // TODO: 27.10.2023 implement
        return null;
    }

    @Override
    public void add(int index, E element) {
        // TODO: 27.10.2023 implement

    }

    @Override
    public E remove(int index) {
        // TODO: 27.10.2023 implement
        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO: 27.10.2023 implement
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        // TODO: 27.10.2023 implement
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // TODO: 27.10.2023 implement
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
//        int subSize = toIndex-fromIndex;
//        List<E> subList = List.of(Arrays.copyOfRange(elementData, fromIndex, toIndex));
//        return new MyArrayList<>();
        return null;
    }

    private class MyIterator implements Iterator<E> {

        private int cursor;
        private int prevValue = -1;

        public MyIterator() {
        }

        @Override
        public boolean hasNext() {
            return size > cursor;
        }

        @Override
        public E next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            prevValue = cursor;
            //noinspection unchecked
            return (E) elementData[cursor++];
        }
    }
}
