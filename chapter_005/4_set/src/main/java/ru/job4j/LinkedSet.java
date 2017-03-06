package ru.job4j;

import java.util.Iterator;

/**
 * LinkedSet class.
 *
 * @param <E> type
 * @author Denis
 * @since 05.03.2017
 */
public class LinkedSet<E> implements SimpleSet<E> {
    /**
     * Last node.
     */
    private Node<E> last;

    /**
     * First node.
     */
    private Node<E> first;

    /**
     * Size.
     */
    private int size;

    /**
     * Empty constructor.
     */
    public LinkedSet() {
    }

    /**
     * Add new element in the end.
     *
     * @param e new element
     */
    @Override
    public void add(E e) {
        if (findNode(e) == null) {
            Node<E> newNode = new Node<E>(this.last, e, null);
            if (this.first == null) {
                this.first = newNode;
            } else {
                if (findNode(e) == null) {
                    last.next = newNode;
                }
            }
            this.last = newNode;
            this.size++;
        }
    }

    /**
     * Find node.
     *
     * @param e element
     * @return node
     */
    private Node<E> findNode(E e) {
        Node<E> result = null;
        Node<E> tmp = first;
        for (int i = 0; i < size; i++) {
            if (e.equals(tmp.element)) {
                result = tmp;
                break;
            }
            tmp = tmp.next;
        }
        return result;
    }

    /**
     * Remove element at specified position.
     *
     * @return
     */
    @Override
    public boolean remove(E e) {
        boolean result = false;
        Node<E> oldValue = findNode(e);
        if (oldValue != null) {
            Node<E> oldPrev = oldValue.prev;
            Node<E> oldNext = oldValue.next;
            if (oldPrev == null) {
                this.first = oldNext;
            } else {
                oldPrev.next = oldNext;
            }
            if (oldNext == null) {
                this.last = oldPrev;
            } else {
                oldNext.prev = oldPrev;
            }
            oldValue.prev = null;
            oldValue.next = null;
            size--;
            result = true;
        }
        return result;
    }

    /**
     * Get size.
     *
     * @return size
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * Iterator class.
     */
    private class Iter implements Iterator<E> {
        /**
         * Current.
         */
        private Node<E> current = first;

        /**
         * Next index.
         */
        private int nextIndex;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public E next() {
            nextIndex++;
            Node<E> result = current;
            current = current.next;
            return result.element;
        }
    }

    /**
     * Node class.
     *
     * @param <E> element
     */
    private static class Node<E> {
        /**
         * Element.
         */
        private E element;

        /**
         * Link on previous.
         */
        private Node<E> prev;

        /**
         * Link on next.
         */
        private Node<E> next;

        /**
         * Main constructor.
         *
         * @param prev    previous node
         * @param element element
         * @param next    next node
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

}
