package ru.jobj4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * IteratorPrime class.
 *
 * @author Denis
 * @since 24.02.2017
 */
public class IteratorPrime implements Iterator {
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
    public IteratorPrime(int[] values) {
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
        findPrime();
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
        findPrime();
        if (this.values.length == this.index) {
            throw new NoSuchElementException();
        }
        return this.values[this.index++];
    }

    /**
     * Find prime number.
     * Setting index to this number.
     */
    private void findPrime() {
        int result = this.values.length;
        boolean prime;
        for (int i = index; i < result; i++) {
            prime = true;
            if (this.values[i] < 2) {
                continue;
            }
            for (int k = 2; k < this.values[i]; k++) {
                if (this.values[i] % k == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                result = i;
                break;
            }
        }
        this.index = result;
    }
}
