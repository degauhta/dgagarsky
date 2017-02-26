package ru.job4j;

/**
 * SimpleArray class.
 *
 * @param <T> type
 * @author Denis
 * @since 25.02.2017
 */
public class SimpleArray<T> {
    /**
     * Objects.
     */
    private Object[] objects;

    /**
     * Current index.
     */
    private int index;

    /**
     * Main constructor.
     *
     * @param size the size of simple array
     */
    SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Add new value in collection.
     *
     * @param value the value to add
     * @return true if value is added to collection
     */
    boolean add(T value) {
        boolean result = false;
        if (this.index < this.objects.length) {
            this.objects[this.index++] = value;
            result = true;
        }
        return result;
    }

    /**
     * Delete first occurrence of value from collection.
     *
     * @param value the value to delete
     * @return true if value is deleted from collection
     */
    boolean delete(T value) {
        boolean result = false;
        if (value != null) {
            for (int i = 0; i < this.index; i++) {
                if (value.equals(this.objects[i])) {
                    this.objects[i] = null;
                    this.index--;
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Get element at index.
     *
     * @param position index of element
     * @return element
     */
    T get(int position) {
        T t = null;
        checkPosition(position);
        t = (T) objects[position];
        return t;
    }

    /**
     * Update value in position.
     *
     * @param value    new value
     * @param position position in collection
     * @return old element
     */
    T update(T value, int position) {
        T oldValue = null;
        checkPosition(position);
        if (position >= this.index) {
            this.index++;
        }
        oldValue = (T) this.objects[position];
        this.objects[position] = value;
        return oldValue;
    }

    /**
     * Check position.
     * If it out of range then throw exception.
     *
     * @param position position
     * @throws IndexOutOfBoundsException if position out of collection
     */
    private void checkPosition(int position) {
        if (position < 0 & position >= this.objects.length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
