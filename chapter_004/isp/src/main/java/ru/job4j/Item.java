package ru.job4j;

/**
 * Item class.
 *
 * @author Denis
 * @since 15.02.2017
 */
public class Item {
    /**
     * Key.
     */
    private int key;

    /**
     * Name.
     */
    private String name;

    /**
     * Child.
     */
    private Item[] child;

    /**
     * Main constructor.
     * @param key key
     * @param name name.
     * @param child child.
     */
    public Item(int key, String name, Item... child) {
        this.key = key;
        this.name = name;
        this.child = child;
    }

    /**
     * Get key.
     * @return key.
     */
    public int getKey() {
        return key;
    }

    /**
     * Get name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get child items.
     * @return child.
     */
    public Item[] getChild() {
        return child;
    }
}
