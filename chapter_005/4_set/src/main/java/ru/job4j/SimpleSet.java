package ru.job4j;

import java.util.Iterator;

/**
 * SimpleSet class.
 *
 * @param <E> type
 * @author Denis
 * @since 05.03.2017
 */
public interface SimpleSet<E> extends Iterable<E> {
    /**
     * Add new element in the end.
     *
     * @param e new element
     */
    void add(E e);

    /**
     * Remove element at specified position.
     *
     * @param e element
     * @return true if deleted
     */
    boolean remove(E e);

    /**
     * Get size.
     *
     * @return size
     */
    int size();

    /**
     * Iterator.
     *
     * @return iterator
     */
    Iterator<E> iterator();
}
