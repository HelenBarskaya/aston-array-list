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

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        // TODO: 28.10.2023 implement 
//        if (a.length < size) {
//            return (T[]) Arrays.copyOf(elementData, a.length, a.getClass());
//        }
//        System.arraycopy(elementData, 0, a, 0, size);
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
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
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
        for (Object element: elementData) {
            element = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Illegal index: " + index);
        }
        //noinspection unchecked
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Illegal index: " + index);
        }
        elementData[index] = element;
        //noinspection unchecked
        return (E) elementData[index];
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Illegal index: " + index);
        } else if (index == size) {
            add(element);
            return;
        } else if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        ++size;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Illegal index: " + index);
        }
        Object element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[--size] = null;
        //noinspection unchecked
        return (E) element;
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
        for (int i = size; i >= 0; i--) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection unchecked
        return List.of((E[]) Arrays.copyOfRange(elementData, fromIndex, toIndex));
    }

    private class MyIterator implements Iterator<E> {

        protected int cursor;
        protected int prevValue = -1;

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

    private class MyListIterator extends MyIterator implements ListIterator<E> {

        public MyListIterator() {
        }

        public MyListIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return (cursor - 1 >= 0 && size > 2);
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            --cursor;
            prevValue = cursor - 1;
            //noinspection unchecked
            return (E) elementData[cursor];
        }

        @Override
        public int nextIndex() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            prevValue = cursor;
            return cursor++;
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            --cursor;
            prevValue = cursor - 1;
            return cursor;
        }

        @Override
        public void remove() {
//            MyArrayList.this.remove(cursor--);
//            prevValue = cursor - 1;
        }

        @Override
        public void set(E e) {
            //elementData[cursor] = e;
        }

        @Override
        public void add(E e) {
            //MyArrayList.this.add(cursor, e);
        }
    }
}
