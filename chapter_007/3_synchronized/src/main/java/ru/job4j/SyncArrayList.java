package ru.job4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SyncArrayList class.
 *
 * @param <E> type
 * @author Denis
 * @since 18.04.2017
 */
class SyncArrayList<E> implements Iterable<E> {
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
    SyncArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    /**
     * Constructor with set size.
     *
     * @param size size
     */
    SyncArrayList(int size) {
        if (size > 0) {
            this.data = new Object[size];
        } else {
            this.data = new Object[DEFAULT_SIZE];
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Add new element in the end.
     *
     * @param e new element
     */
    synchronized void add(E e) {
        checkSize();
        this.data[this.size++] = e;
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
     * Get element from size.
     *
     * @param index size
     * @return element
     */
    @SuppressWarnings("unchecked")
    synchronized E get(int index) {
        checkIndex(index);
        return (E) this.data[index];
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
     * Remove element at specified position.
     *
     * @param index index
     * @return deleted element
     */
    @SuppressWarnings("unchecked")
    synchronized E remove(int index) {
        E old = get(index);
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.size--;
        return old;
    }

    /**
     * Get size.
     *
     * @return size
     */
    synchronized int size() {
        return this.size;
    }

    /**
     * Iterator class.
     */
    private class Itr implements Iterator<E> {
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
        @SuppressWarnings("unchecked")
        public E next() {
            if (current >= size) {
                throw new NoSuchElementException();
            }
            return (E) data[current++];
        }
    }
}
