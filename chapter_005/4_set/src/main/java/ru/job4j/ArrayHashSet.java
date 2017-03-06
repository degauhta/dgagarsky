package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayHashSet class.
 *
 * @param <E> type
 * @author Denis
 * @since 05.03.2017
 */
public class ArrayHashSet<E> implements SimpleSet<E> {
    /**
     * Default size.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Data.
     */
    private Object[] data;

    /**
     * Size.
     */
    private int size;

    /**
     * Main constructor.
     */
    public ArrayHashSet() {
        this.data = new Object[DEFAULT_SIZE];
    }

    /**
     * Add new element.
     *
     * @param e new element
     */
    @Override
    public void add(E e) {
        int index = findIndex(e, this.data.length);
        if (this.data[index] == null) {
            this.data[index] = e;
            this.size++;
        } else if (!e.equals(this.data[index])) {
            reshuffle(e, this.data.length * 2);
            this.size++;
        }
    }

    /**
     * Remove element at specified position.
     *
     * @return true if deleted
     */
    @Override
    public boolean remove(E e) {
        boolean result = false;
        int index = findIndex(e, this.data.length);
        if (e.equals(this.data[index])) {
            this.data[index] = null;
            this.size--;
            result = true;
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
            boolean result = false;
            for (int i = current; i < data.length; i++) {
                if (data[i] != null) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            E result = null;
            for (int i = current; i < data.length; i++) {
                if (data[i] != null) {
                    result = (E) data[i];
                    current = ++i;
                    break;
                }
            }
            if (result == null) {
                throw new NoSuchElementException();
            }
            return result;
        }
    }

    /**
     * Find index.
     *
     * @param e      element
     * @param length length of array
     * @return index
     */
    private int findIndex(E e, int length) {
        return e.hashCode() % length;
    }

    /**
     * Reshuffle data.
     *
     * @param e         element
     * @param newLength new length of array
     */
    private void reshuffle(E e, int newLength) {
        Object[] newData = new Object[newLength];
        int index = findIndex(e, newLength);
        newData[index] = e;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] != null) {
                index = this.data[i].hashCode() % newData.length;
                if (newData[index] != null) {
                    reshuffle(e, newData.length * 2);
                }
                newData[index] = this.data[i];
            }
        }
        this.data = newData;
    }
}
