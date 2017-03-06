package ru.job4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArraySet class.
 *
 * @param <E> type
 * @author Denis
 * @since 05.03.2017
 */
public class ArraySet<E> implements SimpleSet<E> {
    /**
     * Default size.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Data.
     */
    private Object[] data;

    /**
     * Position.
     */
    private int size;

    /**
     * Main constructor.
     */
    public ArraySet() {
        this.data = new Object[DEFAULT_SIZE];
    }

    /**
     * Add new element in the end.
     *
     * @param e new element
     */
    @Override
    public void add(E e) {
        checkSize();
        if (findDuplicate(e) == -1) {
            this.data[this.size++] = e;
        }
    }

    /**
     * Remove element at specified position.
     *
     * @param e element
     * @return deleted element
     */
    @Override
    public boolean remove(E e) {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            if (e.equals(this.data[i])) {
                System.arraycopy(this.data, i + 1, this.data, i, this.size - i - 1);
                this.size--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Get size.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * Iterator class.
     */
    private class Iter implements Iterator<E> {
        /**
         * Current.
         */
        private int current = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current != size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (current >= size) {
                throw new NoSuchElementException();
            }
            return (E) data[current++];
        }

    }

    /**
     * Check index.
     *
     * @param index index
     * @throws IndexOutOfBoundsException exception
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(String.format("index=%s, size=%s", index, this.size));
        }
    }

    /**
     * Check size.
     */
    private void checkSize() {
        if (this.size >= this.data.length) {
            grow();
        }
    }

    /**
     * Grow. Increase array in 2 times.
     */
    private void grow() {
        this.data = Arrays.copyOf(this.data, this.data.length * 2);
    }

    /**
     * Find duplicate.
     *
     * @param e new element
     * @return index or -1
     */
    private int findDuplicate(E e) {
        int result = -1;
        for (int i = 0; i < this.size; i++) {
            if (e.equals(this.data[i])) {
                result = i;
                break;
            }
        }
        return result;
    }
}
