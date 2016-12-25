package ru.job4j.start;

import ru.job4j.models.Item;

/**
 * User interface class.
 *
 * @author Denis
 * @since 24.12.2016
 */
public class StartUI {
    /**
     * Add request.
     */
    private final int addRequest = 1;
    /**
     * Edit request.
     */
    private final int editRequest = 2;
    /**
     * Delete request.
     */
    private final int deleteRequest = 3;
    /**
     * Show all request.
     */
    private final int showAllRequests = 4;
    /**
     * Show filtered request.
     */
    private final int showFilteredRequests = 5;
    /**
     * Exit menu.
     */
    private final int exitMenu = 6;
    /**
     * Console input.
     */
    private ConsoleInput consoleInput = new ConsoleInput();

    /**
     * Main method.
     *
     * @param args - args.
     */
    public static void main(final String[] args) {
        StartUI startUI = new StartUI();
        Tracker tracker = new Tracker();
        int menuCode = -1;
        String name;
        String description;
        String sub;
        do {
            menuCode = startUI.getMenu();
            if (menuCode == startUI.addRequest) {
                name = startUI.consoleInput.ask("Enter name of request");
                description = startUI.consoleInput.ask("Enter description of request");
                tracker.add(new Item(name, description, 1L));
            } else if (menuCode == startUI.editRequest) {
                int position = Integer.parseInt(startUI.consoleInput.ask("Enter number of request:"));
                Item[] items = tracker.getAll();
                if (position < 1 || position > items.length) {
                    System.out.println("Sorry, but you choose not existing request.");
                } else {
                    name = startUI.consoleInput.ask("Enter new name of request");
                    description = startUI.consoleInput.ask("Enter new description of request");
                    Item editedItem = new Item(name, description, 1L);
                    editedItem.setId(items[position - 1].getId());
                    tracker.editRequest(editedItem);
                }
            } else if (menuCode == startUI.deleteRequest) {
                int position = Integer.parseInt(startUI.consoleInput.ask("Enter number of request:")) + 1;
                Item[] items = tracker.getAll();
                if (position < 1 || position > items.length) {
                    System.out.println("Sorry, but you choose not existing request.");
                } else {
                    tracker.removeRequest(items[position - 1]);
                }
            } else if (menuCode == startUI.showAllRequests) {
                int i = 1;
                for (Item items : tracker.getAll()) {
                    System.out.println(i++ + ") name=" + items.getName()
                            + ", description=" + items.getDescription());
                }
            } else if (menuCode == startUI.showFilteredRequests) {
                int i = 1;
                sub = startUI.consoleInput.ask("Enter substring for search: ");
                for (Item items : tracker.filterRequest(sub)) {
                    System.out.println(i++ + ") name=" + items.getName()
                            + ", description=" + items.getDescription());
                }
            }
            System.out.println();
        } while (menuCode != startUI.exitMenu);
    }

    /**
     * Work with menu.
     *
     * @return user choose.
     */
    private int getMenu() {
        String[] mainMenu = {"1. Add new request.",
                "2. Edit request",
                "3. Delete request",
                "4. Show all request",
                "5. Show filtered request",
                "6. Exit"};
        return consoleInput.chooseMenu(mainMenu);
    }
}
