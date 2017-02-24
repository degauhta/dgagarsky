package ru.jobj4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * IteratorEven class.
 *
 * @author Denis
 * @since 23.02.2017
 */
public class IteratorEven implements Iterator {
    /**
     * Values.
     */
    private final int[] values;

    /**
     * Index.
     */
    private int index = 0;

    /**
     * Main constructor.
     *
     * @param values values.
     */
    public IteratorEven(int[] values) {
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
        findEven();
        return this.values.length > this.index;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        findEven();
        if (this.values.length == this.index) {
            throw new NoSuchElementException();
        }
        return this.values[this.index++];
    }

    /**
     * Find even number.
     * Setting index to this number.
     */
    private void findEven() {
        int result = this.values.length;
        for (int i = this.index; i < this.values.length; i++) {
            if (this.values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        this.index = result;
    }
}
