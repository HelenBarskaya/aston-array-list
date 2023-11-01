package org.example;

import java.util.*;

/**
 * Класс динамического массива, имплементирующий интерфейс {@link List}. Класс реализует методы интерфейса {@link List}, а также
 * имеет методы для управления размером массива, который используется для хранения элементов списка. Разрешены все
 * типы объектов.
 * <p/>
 * Каждый экземпляр MyArrayList имеет <i>capacity</i>. Capacity - количество элементов, которые могут храниться в массиве. При
 * добавлении нового элемента, если достигнут предел заполненности массива, он увеличивается.
 * <p/>
 * Операции {@code size}, {@code isEmpty}, {@code get}, {@code set}, {@code iterator} и {@code ListIterator} выполняются за постоянное время.
 * Операция добавления выполняется за {@code O(n)} времени для {@code n} элементов. Все остальные операции выполняются за линейное время.
 * <p>
 * <strong>Эта реализация не синхронизирована.</strong>
 *
 * @author Бараковская Елена
 */
public class MyArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ARRAY = {};

    private Object[] elementData;

    private int size = 0;

    /**
     * Конструктор по умолчанию, создаёт пустой список емкостью 10.
     */
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, создающий список с емкостью {@code capacity}.
     *
     * @param capacity емкость списка
     * @throws IllegalArgumentException при отрицательном значении capacity
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal size: " + capacity);
        }
    }

    /**
     * Конструктор, создающий список из коллекции.
     *
     * @param collection коллекция, элементы которой будут храниться в списке
     */
    public MyArrayList(Collection<? extends E> collection) {
        if (!collection.isEmpty()) {
            this.size = collection.size();
            elementData = Arrays.copyOf(collection.toArray(), size, Object[].class);
        } else {
            elementData = EMPTY_ARRAY;
        }
    }

    private void grow(int minSize) {
        elementData = Arrays.copyOf(elementData, (minSize * 3) / 2 + 1);
    }

    /**
     * @return количество ненулевых элементов в массиве
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return {@code false}, если в массиве есть ненулевые элементы, в противном случае - {@code true}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param o объект, наличие которого в массиве проверяется
     * @return {@code true}, если в массиве есть искомый элемент, в противном случае - {@code false}
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * @return возвращает итератор {@link MyIterator} для обхода списка
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * @return список, преобразованный в массив Object[]
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Заполняет переданный массив значениями из текущего списка.
     *
     * @param a   типизированный массив для сохранения элементов списка
     * @param <T> тип заполняемого массива
     * @return список, преобразованный в типизированный массив
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * Добавляет новый элемент в конец списка.
     *
     * @param e элемент для добавления
     * @return {@code true}, если элемент был успешно добавлен
     */
    @Override
    public boolean add(E e) {
        int oldSize = size;
        if (size == elementData.length) {
            grow(size);
        }
        elementData[oldSize] = e;
        ++size;
        return true;
    }

    /**
     * Удаляет объект из списка.
     *
     * @param o элемент для удаления
     * @return {@code true} если удаление прошло успешно, в противном случае - {@code false}
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Проверяет наличие всех элементов коллекции внутри списка.
     *
     * @param c коллекция, наличие элементов которой проверяется
     * @return {@code true} если все элементы коллекции находятся в списке, в противном случае - {@code false}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) return false;
        }
        return true;
    }

    /**
     * Добавляет в список все элементы коллекции.
     *
     * @param c коллекция, элементы которой добавляются в список
     * @return {@code true} если добавление прошло успешно, в противном случае - {@code false}
     * @throws IndexOutOfBoundsException если коллекция пуста
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * Добавляет в список все элементы коллекции, начиная с позиции {@code index}.
     *
     * @param index позиция, по которой вставляется первый элемент из переданной коллекции
     * @param c     коллекция, элементы которой добавляются в список
     * @return {@code true} если добавление прошло успешно, в противном случае - {@code false}
     * @throws IndexOutOfBoundsException если передано некорректное значение индекса
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        if (index < 0 || index > c.size()) {
            throw new IndexOutOfBoundsException();
        }

        Object[] additionalArray = c.toArray();
        int newSize = size + additionalArray.length;
        if (newSize > elementData.length) {
            grow(newSize);
        }
        for (int i = 0; i < additionalArray.length; i++) {
            //noinspection unchecked
            add(index + i, (E) additionalArray[i]);
        }
        size = newSize;
        return true;
    }

    /**
     * Удаляет из списка элементы, которые содержатся в коллекции.
     *
     * @param c коллекция, содержащая элементы, которые требуется удалить из списка
     * @return {@code true} если элементы были удалены, в противном случае - {@code false}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        Object[] collection = c.toArray();
        int index;
        for (int i = 0; i < c.size(); i++) {
            while ((index = indexOf(collection[i])) != -1) {
                remove(index);
            }
        }
        return true;
    }

    /**
     * Удаляет из списка элементы, которых нет в коллекции.
     *
     * @param c коллекция, элементы которой должны остаться в списке
     * @return {@code true} если элементы были удалены, в противном случае - {@code false}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        Object[] collection = c.toArray();
        Object[] newList = new Object[elementData.length];
        int newSize = 0;
        int index;
        for (int i = 0, j = 0; i < c.size(); i++) {
            while ((index = indexOf(collection[i])) != -1) {
                newList[j++] = elementData[index];
                ++newSize;
                remove(index);
            }
        }
        elementData = newList;
        size = newSize;
        return true;
    }

    /**
     * Удаляет все элементы массива.
     */
    @SuppressWarnings("UnusedAssignment")
    @Override
    public void clear() {
        for (Object element : elementData) {
            element = null;
        }
        size = 0;
    }

    /**
     * Возвращает элемент списка по индексу.
     *
     * @param index индекс искомого элемента
     * @return элемент списка с соответствующим индексом
     * @throws NoSuchElementException если индекс меньше 0 или больше чем максимальный существующий индекс
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Illegal index: " + index);
        }
        //noinspection unchecked
        return (E) elementData[index];
    }

    /**
     * Заменяет элемент списка по индексу.
     *
     * @param index   индекс заменяемого элемента
     * @param element элемент, который необходимо сохранить по индексу
     * @return элемент списка с соответствующим индексом
     * @throws NoSuchElementException если индекс меньше 0 или больше чем максимальный существующий индекс
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Illegal index: " + index);
        }
        elementData[index] = element;
        //noinspection unchecked
        return (E) elementData[index];
    }

    /**
     * Добавляет элемент в список по индексу.
     *
     * @param index   индекс по которому добавляется элемент
     * @param element элемент, который необходимо добавить
     * @throws NoSuchElementException – если индекс меньше 0 или больше чем размер списка
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Illegal index: " + index);
        } else if (index == size) {
            add(element);
            return;
        } else if (size == elementData.length) {
            grow(size);
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        ++size;
    }

    /**
     * Удаляет элемент списка по индексу.
     *
     * @param index индекс элемента, который должен быть удален
     * @return удаленный элемент
     * @throws NoSuchElementException если индекс меньше 0 или больше чем максимальный существующий индекс
     */
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

    /**
     * Возвращает индекс элемента в списке.
     *
     * @param o элемент, индекс которого необходимо получить
     * @return индекс элемента в списке или -1, если элемент не был найден
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает последний индекс элемента в списке.
     *
     * @param o элемент, индекс которого необходимо получить
     * @return последний индекс элемента в списке или -1, если элемент не был найден
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i >= 0; i--) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return {@link MyListIterator} для обхода коллекций
     */
    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    /**
     * @param index позиция курсора в итераторе
     * @return {@link MyListIterator} для обхода коллекций
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    /**
     * Возвращает часть списка, заключенную между левой и правой границами. Возвращаемый список является
     * объектом типа {@link List} и поддерживает все соответствующие операции.
     *
     * @param fromIndex левая граница подсписка (включена в результат)
     * @param toIndex   правая граница подсписка (не включена в результат)
     * @return {@link List}, заполненный элементами из выделенного диапазона
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection unchecked
        return List.of((E[]) Arrays.copyOfRange(elementData, fromIndex, toIndex));
    }

    /**
     * Класс итератора, реализующий интерфейс {@link Iterator}. Позволяет обходить коллекцию в цикле {@code for each} и
     * удалять элементы во время обхода.
     */
    private class MyIterator implements Iterator<E> {

        protected int nextIndex;
        protected int prevIndex = -1;

        /**
         * Конструктор по умолчанию.
         */
        public MyIterator() {
        }

        /**
         * @return {@code true} если существует следующий элемент в коллекции, в противном случае - {@code false}
         */
        @Override
        public boolean hasNext() {
            return size > nextIndex;
        }

        /**
         * @return следующий элемент в коллекции
         * @throws NoSuchElementException если следующего элемента не существует
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prevIndex = nextIndex;
            //noinspection unchecked
            return (E) elementData[nextIndex++];
        }

        /**
         * Удаляет предыдущий возвращенный итератором элемент.
         *
         * @throws NoSuchElementException если следующего элемента не существует
         */
        @Override
        public void remove() {
            if (prevIndex < 0) {
                throw new NoSuchElementException();
            }
            nextIndex = prevIndex;
            MyArrayList.this.remove(elementData[prevIndex--]);
        }
    }

    /**
     * Класс, который позволяет обходить коллекцию в обе стороны. Расширяет класс {@link MyIterator} и реализует
     * интерфейс {@link ListIterator}.
     */
    private class MyListIterator extends MyIterator implements ListIterator<E> {

        /**
         * Конструктор по умолчанию.
         */
        public MyListIterator() {
            super();
        }

        /**
         * Конструктор, создающий MyListIterator с начальной позиции {@code index}.
         *
         * @param index индекс стартового элемента
         * @throws NoSuchElementException если элемента с таким индексом нет в списке
         */
        public MyListIterator(int index) {
            super();
            nextIndex = index;
            if (index >= 0 && index < size) {
                prevIndex = index - 1;
            } else throw new NoSuchElementException();
        }

        /**
         * @return {@code true} если есть предыдущий элемент в коллекции, в противном случае - {@code false}
         */
        @Override
        public boolean hasPrevious() {
            return (prevIndex > 0);
        }

        /**
         * Возвращает предыдущий элемент в списке и перемещает положение курсора назад.
         *
         * @return предыдущий элемент в списке
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            nextIndex = prevIndex;
            --prevIndex;
            //noinspection unchecked
            return (E) elementData[prevIndex];
        }

        /**
         * Возвращает индекс элемента, который был бы возвращен при последующем вызове {@link MyListIterator#next()}.
         *
         * @return индекс элемента
         * @throws NoSuchElementException если в списке нет следующего элемента
         */
        @Override
        public int nextIndex() {
            if (nextIndex >= size) {
                throw new NoSuchElementException();
            }
            return nextIndex;
        }

        /**
         * Возвращает индекс элемента, который был бы возвращен при последующем вызове {@link MyListIterator#previous()}.
         *
         * @return индекс элемента
         * @throws NoSuchElementException если предыдущего элемента нет
         */
        @Override
        public int previousIndex() {
            if (prevIndex < 0) {
                throw new NoSuchElementException();
            }
            return prevIndex;
        }

        /**
         * Заменяет последний элемент, возвращенный с помощью {@link MyListIterator#next()}
         * или {@link MyListIterator#previous()}, указанным элементом.
         *
         * @param e заменяет последний элемент, возвращенный с помощью
         *          {@link MyListIterator#next()} или {@link MyListIterator#previous()}
         * @throws NoSuchElementException если последнего элемента нет
         */
        @Override
        public void set(E e) {
            if (prevIndex < 0) {
                throw new NoSuchElementException();
            }
            MyArrayList.this.set(prevIndex, e);
        }

        /**
         * Вставляет указанный элемент в список.
         *
         * @param e элемент для вставки
         */
        @Override
        public void add(E e) {
            MyArrayList.this.add(nextIndex, e);
        }
    }
}
