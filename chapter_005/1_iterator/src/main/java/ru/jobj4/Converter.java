package ru.jobj4;

import java.util.Iterator;

/**
 * Converter class.
 *
 * @author Denis
 * @since 24.02.2017
 */
public interface Converter {
    /**
     * Convert n-iterators in one iterator.
     *
     * @param it iterator of iterators.
     * @return iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
