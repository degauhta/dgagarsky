package ru.job4j;

/**
 * MenuItem class.
 *
 * @author Denis
 * @since 15.02.2017
 */
public class MenuItem implements Menu, Action {
    /**
     * Actions.
     */
    private final Item[] items;

    /**
     * Main constructor.
     *
     * @param items items.
     */
    public MenuItem(final Item... items) {
        this.items = items;
    }

    /**
     * Get items.
     *
     * @return items.
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Print menu items in console.
     *
     * @param items  items.
     * @param prefix prefix of child action.
     */
    @Override
    public void show(Item[] items, String prefix) {
        for (Item item : items) {
            if (item == null) {
                break;
            }
            System.out.println(prefix + item.getName());
            show(item.getChild(), String.format("--%s", prefix));
        }
    }

    /**
     * Execute.
     *
     * @param key menu key.
     */
    @Override
    public void execute(int key) {
        System.out.printf("Execution %s", this.items[key].getName());
    }
}