package ru.job4j;

/**
 * Store class.
 *
 * @param <T> type
 * @author Denis
 * @since 26.02.2017
 */
public interface Store<T extends Base> {
    /**
     * Add element to collection.
     *
     * @param value new element
     * @return true if added successfully
     */
    boolean add(T value);

    /**
     * Remove element from collection.
     *
     * @param id element id
     * @return true if remove successfully
     */
    boolean remove(String id);

    /**
     * Update element in collection.
     *
     * @param newValue new element
     * @param id old element id
     * @return old element
     */
    T update(T newValue, String id);

}
