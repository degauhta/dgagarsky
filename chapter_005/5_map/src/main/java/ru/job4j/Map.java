package ru.job4j;

import java.util.Iterator;

/**
 * Map class.
 *
 * @param <T> key
 * @param <V> value
 * @author Denis
 * @since 09.03.2017
 */
public interface Map<T, V> extends Iterable {
    /**
     * Add element.
     *
     * @param key   key
     * @param value value
     * @return true if added successfully
     */
    boolean insert(T key, V value);

    /**
     * Get value by key.
     *
     * @param key key
     * @return value
     */
    V get(T key);

    /**
     * Delete entity by key.
     *
     * @param key key
     * @return true if deleted
     */
    boolean delete(T key);

    /**
     * Get number of nodes stored in collection.
     *
     * @return size
     */
    int getSize();

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    Iterator iterator();
}