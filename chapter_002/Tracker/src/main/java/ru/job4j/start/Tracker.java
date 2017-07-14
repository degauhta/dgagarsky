package ru.job4j.start;

import ru.job4j.models.Item;

/**
 * Tracker class.
 *
 * @author Denis
 * @since 11.07.2017
 */
public interface Tracker {
    /**
     * Add request to storage.
     *
     * @param item request.
     * @return ref to request.
     */
    public Item add(Item item);

    /**
     * Find request. Searching in fields name and description.
     *
     * @param sub substring to find.
     * @return finding request.
     */
    public Item[] filterRequest(String sub);

    /**
     * Print all requests.
     *
     * @return all requests.
     */
    public Item[] getAll();

    /**
     * Editing request.
     *
     * @param item request to edit.
     * @return edited request or null if request not found in tracker.
     */
    public Item editRequest(Item item);

    /**
     * @param removeItem request that need to delete.
     * @return true if remove successful.
     */
    public boolean removeRequest(Item removeItem);
}