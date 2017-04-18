package ru.job4j;

import java.util.Iterator;

/**
 * SyncLinkedList class.
 *
 * @param <E> type
 * @author Denis
 * @since 18.04.2017
 */
class SyncLinkedList<E> implements Iterable<E> {
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
    SyncLinkedList() {
    }

    /**
     * Add new element in the end.
     *
     * @param e new element
     */
    synchronized void add(E e) {
        Node<E> newNode = new Node<>(this.last, e, null);
        if (this.first == null) {
            this.first = newNode;
        } else {
            last.next = newNode;
        }
        this.last = newNode;
        this.size++;
    }

    /**
     * Get element from position.
     *
     * @param index position
     * @return element
     */
    synchronized E get(int index) {
        checkIndex(index);
        return getNode(index).element;
    }

    /**
     * Get node.
     *
     * @param index index
     * @return node
     */
    private Node<E> getNode(int index) {
        Node<E> result;
        if (index < this.size / 2) {
            result = first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = last;
            for (int i = size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }

    /**
     * Check index.
     *
     * @param index index
     * @throws IndexOutOfBoundsException exception
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(String.format("index=%s, size=%s", index, this.size));
        }
    }

    /**
     * Remove element at specified position.
     *
     * @param index index
     * @return deleted element
     */
    synchronized E remove(int index) {
        checkIndex(index);
        Node<E> oldValue = getNode(index);
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
        return oldValue.element;
    }

    /**
     * Get size.
     *
     * @return size
     */
    synchronized int size() {
        return this.size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Iterator class.
     */
    private class Itr implements Iterator<E> {
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
