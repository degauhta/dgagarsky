package ru.job4j;

/**
 * Menu class.
 *
 * @author Denis
 * @since 15.02.2017
 */
public interface Menu {
    /**
     * Print menu items in console.
     *
     * @param items items.
     * @param prefix  prefix of child action.
     */
    void show(Item[] items, String prefix);
}
