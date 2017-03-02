package ru.job4j;

/**
 * SimpleContainer class.
 *
 * @param <E> type
 * @author Denis
 * @since 01.03.2017
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Add new element in the end.
     *
     * @param e new element
     */
    void add(E e);

    /**
     * Get element from position.
     *
     * @param index position
     * @return element
     */
    E get(int index);

    /**
     * Remove element at specified position.
     *
     * @param index index
     * @return deleted element
     */
    E remove(int index);

    /**
     * Get size.
     *
     * @return size
     */
    int size();
}
