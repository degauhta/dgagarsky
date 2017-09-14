package ru.dega.dao;

import ru.dega.models.Item;

import java.util.List;


/**
 * ItemDao class.
 *
 * @author Denis
 * @since 11.09.2017
 */
public interface ItemDao {
    /**
     * Create to-do item.
     *
     * @param item item
     * @return true if create successfully
     */
    boolean createItem(Item item);

    /**
     * Update to-do item.
     *
     * @param item updated item
     * @return true if update successfully
     */
    boolean updateItem(Item item);

    /**
     * Delete to-do item by id.
     *
     * @param item item
     * @return true if create successfully
     */
    boolean deleteItem(Item item);

    /**
     * Get all to-do items.
     *
     * @return list of items
     */
    List<Item> getAllItems();

    /**
     * Get to-do items with status not done.
     *
     * @return list of items
     */
    List<Item> getNotDoneItems();
}