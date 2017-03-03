package ru.job4j;

/**
 * SimpleStack class.
 *
 * @param <E> type
 * @author Denis
 * @since 03.03.2017
 */
public interface SimpleStack<E> {
    /**
     * Push element to the collection.
     *
     * @param e new element
     */
    void push(E e);

    /**
     * Poll element out of the collection.
     *
     * @return collection element
     */
    E poll();
}
