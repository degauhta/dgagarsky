package ru.job4j;

/**
 * LinkedStack class.
 *
 * @param <E> type
 * @author Denis
 * @since 03.03.2017
 */
public class LinkedStack<E> extends LinkedContainer<E> implements SimpleStack<E> {
    /**
     * Push element to the collection.
     *
     * @param e new element
     */
    @Override
    public void push(E e) {
        add(e);
    }

    /**
     * Poll element out of the collection.
     *
     * @return top element
     */
    @Override
    public E poll() {
        return remove(size() - 1);
    }
}
