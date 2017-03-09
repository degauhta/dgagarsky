package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Catalog class.
 *
 * @param <T> key
 * @param <V> value
 * @author Denis
 * @since 08.03.2017
 */
public class Catalog<T, V> implements Map<T, V> {
    /**
     * Table with data.
     */
    private Node<T, V>[] table;

    /**
     * Size.
     */
    private int size;

    /**
     * Main constructor.
     */
    public Catalog() {
        this.table = new Node[2];
        this.size = 0;
    }

    /**
     * Add element.
     *
     * @param key   key
     * @param value value
     * @return true if added successfully
     */
    public boolean insert(T key, V value) {
        int i = calculateTableIndex(key);
        if (table[i] == null) {
            table[i] = new Node<T, V>(key, value);
            size++;
        } else {
            Node<T, V> current = table[i];
            while (true) {
                if (current.hash == key.hashCode()) {
                    current.value = value;
                    break;
                } else if (current.next == null) {
                    current.next = new Node<T, V>(key, value);
                    size++;
                    break;
                }
                current = current.next;
            }
        }
        return true;
    }

    /**
     * Get value by key.
     *
     * @param key key
     * @return value
     */
    public V get(T key) {
        int i = calculateTableIndex(key);
        V result = null;
        if (table[i] != null) {
            if (table[i].hash == key.hashCode()) {
                result = table[i].value;
            } else {
                Node<T, V> current = table[i];
                while (current.next != null) {
                    current = current.next;
                    if (current.hash == key.hashCode()) {
                        result = current.value;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Delete entity by key.
     *
     * @param key key
     * @return true if deleted
     */
    public boolean delete(T key) {
        boolean result = false;
        int i = calculateTableIndex(key);
        if (table[i] != null) {
            Node<T, V> current = table[i];
            if (current.hash == key.hashCode()) {
                table[i] = current.next;
                size--;
                result = true;
            } else {
                Node<T, V> prev = current;
                current = current.next;
                while (current != null) {
                    if (current.hash == key.hashCode()) {
                        prev.next = current.next;
                        result = true;
                        size--;
                        break;
                    }
                    prev = current;
                    current = current.next;
                }
            }
        }
        return result;
    }

    /**
     * Get number of nodes stored in collection.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Get table index.
     *
     * @param key key
     * @return index
     */
    private int calculateTableIndex(T key) {
        return key.hashCode() % table.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterat();
    }

    /**
     * Class iterator.
     */
    private class Iterat implements Iterator {
        /**
         * Position.
         */
        private int position = 0;

        /**
         * Current node.
         */
        private Node<T, V> current;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            int pos = position;
            if (current != null) {
                result = current.next != null;
            }
            if (current == null || !result) {
                while (pos < table.length) {
                    if (table[pos] != null) {
                        result = true;
                        break;
                    }
                    pos++;
                }
            }
            return result;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public V next() {
            if (current != null) {
                current = current.next;
            } else {
                while (position < table.length) {
                    if (table[position] != null) {
                        current = table[position++];
                        break;
                    }
                    position++;
                }
            }
            if (current == null && position == table.length) {
                throw new NoSuchElementException();
            }
            return current.value;
        }
    }

    /**
     * Node.
     *
     * @param <T> key
     * @param <V> value
     */
    private class Node<T, V> {
        /**
         * Hash.
         */
        private int hash;

        /**
         * Key.
         */
        private T key;

        /**
         * Value.
         */
        private V value;

        /**
         * Next node.
         */
        private Node<T, V> next;

        /**
         * Main constructor.
         *
         * @param key   key
         * @param value value
         */
        Node(T key, V value) {
            this.hash = key.hashCode();
            this.key = key;
            this.value = value;
        }
    }
}
