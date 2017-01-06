package ru.job4j.start;

import ru.job4j.models.Item;

/**
 * MenuTracker class.
 *
 * @author Denis
 * @since 30.12.2016
 */
public class MenuTracker {
    /**
     * Input.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     *
     */
    private final int menuSize = 5;
    /**
     * Array of user action.
     */
    private UserAction[] userActions = new UserAction[menuSize];

    /**
     * Constructor.
     *
     * @param input   .
     * @param tracker .
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Fill userActions.
     * @return array of possible user action.
     */
    public int[] fillAction() {
        final int showItems = 3;
        final int filterItems = 4;
        this.userActions[0] = new AddItem("Add the new item.");
        this.userActions[1] = new EditItem("Edit item.");
        this.userActions[2] = new DeleteItem("Delete item.");
        this.userActions[showItems] = new ShowItems("Show all items.");
        this.userActions[filterItems] = new FilterItems("Filter items.");
        int[] possibleAction = new int[userActions.length];
        for (int i = 0; i < this.userActions.length; i++) {
            possibleAction[i] = this.userActions[i].key();
        }
        return possibleAction;
    }

    /**
     * Run selected action.
     * @param key of action.
     */
    public void select(int key) {
        this.userActions[key].execute(input, tracker);
    }

    /**
     * Show all userAction in console.
     */
    public void show() {
        for (UserAction userAction : userActions) {
            System.out.println(userAction.info());
        }
    }

    /**
     * Menu item - Add Item.
     */
    private class AddItem extends BaseAction {
        /**
         * Constructor.
         * @param name of action.
         */
        AddItem(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 0;
        }

        /**
         * Add item to tracker.
         *
         * @param input   input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name of request");
            String description = input.ask("Enter description of request");
            tracker.add(new Item(name, description, 1L));
        }
    }

    /**
     * Menu item - Edit Item.
     */
    private class EditItem extends BaseAction {
        /**
         * Constructor.
         * @param name of action.
         */
        EditItem(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 1;
        }

        /**
         * Add item to tracker.
         *
         * @param input   input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.getAll();
            int[] range = new int[items.length];
            for (int i = 0; i < range.length;) {
                range[i] = ++i;
            }
            int position = input.ask("Enter number of request: ", range);
            String name = input.ask("Enter new name of request");
            String description = input.ask("Enter new description of request");
            Item editedItem = new Item(name, description, 1L);
            editedItem.setId(items[position - 1].getId());
            tracker.editRequest(editedItem);
        }
    }

    /**
     * Menu item - delete item.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Constructor.
         * @param name of action.
         */
        DeleteItem(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 2;
        }

        /**
         * Add item to tracker.
         *
         * @param input   input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.getAll();
            int[] range = new int[items.length];
            for (int i = 0; i < range.length;) {
                range[i] = ++i;
            }
            int position = input.ask("Enter number of request: ", range);
            tracker.removeRequest(items[position - 1]);
        }
    }

    /**
     * Menu item - show items.
     */
    private class ShowItems extends BaseAction {
        /**
         * Constructor.
         * @param name of action.
         */
        ShowItems(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            final int three = 3;
            return three;
        }

        /**
         * Add item to tracker.
         *
         * @param input   input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            int i = 1;
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s. %s - %s", i++, item.getName(), item.getDescription()));
            }
        }
    }

    /**
     * Menu item - filter items.
     */
    private class FilterItems extends BaseAction {
        /**
         * Constructor.
         * @param name of action.
         */
        FilterItems(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            final int four = 4;
            return four;
        }

        /**
         * Add item to tracker.
         *
         * @param input   input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            int i = 1;
            String sub = input.ask("Enter substring for search: ");
            for (Item item : tracker.filterRequest(sub)) {
                System.out.println(String.format("%s. %s - %s", i++, item.getName(), item.getDescription()));
            }
        }
    }
}