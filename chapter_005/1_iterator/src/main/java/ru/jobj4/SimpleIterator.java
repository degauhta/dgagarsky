package ru.jobj4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleIterator class.
 *
 * @param <T> type.
 * @author Denis
 * @since 25.02.2017
 */
public class SimpleIterator<T> implements Iterator<T> {
    /**
     * Values.
     */
    private final T[] values;

    /**
     * Index.
     */
    private int index = 0;

    /**
     * Main constructor.
     *
     * @param values values.
     */
    public SimpleIterator(T... values) {
        this.values = values;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return this.values.length > this.index;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        if (this.values.length == this.index) {
            throw new NoSuchElementException();
        }
        return this.values[this.index++];
    }
}
