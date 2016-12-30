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
     */
    public void fillAction() {
        final int showItems = 3;
        final int filterItems = 4;
        this.userActions[0] = new AddItem();
        this.userActions[1] = new EditItem();
        this.userActions[2] = new DeleteItem();
        this.userActions[showItems] = new ShowItems();
        this.userActions[filterItems] = new FilterItems();
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
    private class AddItem implements UserAction {
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

        /**
         * Menu desc.
         *
         * @return description.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    /**
     * Menu item - Edit Item.
     */
    private class EditItem implements UserAction {
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
            int position = Integer.parseInt(input.ask("Enter number of request:"));
            Item[] items = tracker.getAll();
            if (position < 1 || position > items.length) {
                System.out.println("Sorry, but you choose not existing request.");
            } else {
                String name = input.ask("Enter new name of request");
                String description = input.ask("Enter new description of request");
                Item editedItem = new Item(name, description, 1L);
                editedItem.setId(items[position - 1].getId());
                tracker.editRequest(editedItem);
            }
        }

        /**
         * Menu desc.
         *
         * @return description.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item.");
        }
    }

    /**
     * Menu item - delete item.
     */
    private class DeleteItem implements UserAction {
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
            int position = Integer.parseInt(input.ask("Enter number of request:"));
            Item[] items = tracker.getAll();
            if (position < 1 || position > items.length) {
                System.out.println("Sorry, but you choose not existing request.");
            } else {
                tracker.removeRequest(items[position - 1]);
            }
        }

        /**
         * Menu desc.
         *
         * @return description.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item.");
        }
    }

    /**
     * Menu item - show items.
     */
    private class ShowItems implements UserAction {
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
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
        }

        /**
         * Menu desc.
         *
         * @return description.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    /**
     * Menu item - filter items.
     */
    private class FilterItems implements UserAction {
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
            for (Item items : tracker.filterRequest(sub)) {
                System.out.println(i++ + ") name=" + items.getName()
                        + ", description=" + items.getDescription());
            }
        }

        /**
         * Menu desc.
         *
         * @return description.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Filter items.");
        }
    }
}