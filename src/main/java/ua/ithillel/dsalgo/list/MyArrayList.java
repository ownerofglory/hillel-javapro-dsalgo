package ua.ithillel.dsalgo.list;

import ua.ithillel.dsalgo.util.ArrayUtil;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] array;
    private int size;

    public MyArrayList() {
        array = ArrayUtil.getGenericArray(DEFAULT_LENGTH);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T el) {
        ensureCapacity();

        array[size++] = el;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            grow();
        }
    }

    private void grow() {
        int newLength = (int) ((int) array.length * GROW_FACTOR);

        array = Arrays.copyOf(array, newLength);
    }

    @Override
    public void set(int index, T el) {
        array[index] = el;
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Size exceeded");
        }
        return array[index];
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Size exceeded");
        }

        // 3 4 5 6 8 2 1
        //       ^
        // 3 4 5 _ 8 2 1
        //

        final T removed = array[index];

        System.arraycopy(array, index + 1, array, index, array.length - (index + 1));

        size--;

        return removed;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int curIdx;

        @Override
        public boolean hasNext() {
            return curIdx != size;
        }

        @Override
        public T next() {
            return array[curIdx++];
        }
    }
}
