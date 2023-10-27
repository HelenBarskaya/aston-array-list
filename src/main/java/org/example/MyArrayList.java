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
        // TODO: 27.10.2023 implement
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO: 27.10.2023 implement
        return null;
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
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
