package ru.jobj4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterators class.
 *
 * @author Denis
 * @since 25.02.2017
 */
public class Iterators implements Converter, Iterator {
    /**
     * Iterators.
     */
    private Iterator<Iterator<Integer>> iteratorIterator;

    /**
     * Current iterator.
     */
    private Iterator<Integer> currentIterator;

    /**
     * Convert n-iterators in one iterator.
     *
     * @param it iterator of iterators.
     * @return iterator.
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.iteratorIterator = it;
        if (it != null) {
            this.currentIterator = this.iteratorIterator.next();
        }
        return this;
    }

    /**
     * Check if the iterator of iterators has more elements.
     *
     * @return {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        boolean result = this.currentIterator.hasNext();
        if (!result & this.iteratorIterator.hasNext()) {
            do {
                this.currentIterator = this.iteratorIterator.next();
                if (this.currentIterator != null) {
                    result = this.currentIterator.hasNext();
                }
            } while (!result || this.iteratorIterator.hasNext());
        }
        return result;
    }

    /**
     * Returns the next element in the iterator of iterators.
     *
     * @return the next element in the iterator of iterators.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentIterator.next();
    }
}
