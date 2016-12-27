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
     * Console input.
     */
    private Input input;

    /**
     * Constructor.
     *
     * @param input console input.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * Main method.
     *
     * @param args - args.
     */
    public static void main(final String[] args) {
        Input input = new ConsoleInput();
        new StartUI(input).init();
    }

    /**
     * Initialization.
     */
    public void init() {
        final int addRequest = 1;
        final int editRequest = 2;
        final int deleteRequest = 3;
        final int showAllRequests = 4;
        final int showFilteredRequests = 5;
        final int exitMenu = 6;
        Tracker tracker = new Tracker();

        int menuCode = -1;
        String name;
        String description;
        String sub;
        do {
            menuCode = this.getMenu();
            if (menuCode == addRequest) {
                name = input.ask("Enter name of request");
                description = input.ask("Enter description of request");
                tracker.add(new Item(name, description, 1L));
            } else if (menuCode == editRequest) {
                int position = Integer.parseInt(input.ask("Enter number of request:"));
                Item[] items = tracker.getAll();
                if (position < 1 || position > items.length) {
                    System.out.println("Sorry, but you choose not existing request.");
                } else {
                    name = input.ask("Enter new name of request");
                    description = input.ask("Enter new description of request");
                    Item editedItem = new Item(name, description, 1L);
                    editedItem.setId(items[position - 1].getId());
                    tracker.editRequest(editedItem);
                }
            } else if (menuCode == deleteRequest) {
                int position = Integer.parseInt(input.ask("Enter number of request:"));
                Item[] items = tracker.getAll();
                if (position < 1 || position > items.length) {
                    System.out.println("Sorry, but you choose not existing request.");
                } else {
                    tracker.removeRequest(items[position - 1]);
                }
            } else if (menuCode == showAllRequests) {
                int i = 1;
                for (Item items : tracker.getAll()) {
                    System.out.println(i++ + ") name=" + items.getName()
                            + ", description=" + items.getDescription());
                }
            } else if (menuCode == showFilteredRequests) {
                int i = 1;
                sub = input.ask("Enter substring for search: ");
                for (Item items : tracker.filterRequest(sub)) {
                    System.out.println(i++ + ") name=" + items.getName()
                            + ", description=" + items.getDescription());
                }
            }
            System.out.println();
        } while (menuCode != exitMenu);
    }

    /**
     * Work with menu.
     *
     * @return user choose.
     */
    private int getMenu() {
        String newLine = System.getProperty("line.separator");
        System.out.println("Main menu:");
        String mainMenu = "1) Add new request" + newLine + "2) Edit request"
                + newLine + "3) Delete request" + newLine + "4) Show all request"
                + newLine + "5) Show filtered request" + newLine + "6) Exit"
                + newLine + "choose menu element:";
        int result = -1;
        int menuCount = (mainMenu.length() - mainMenu.replaceAll("[1234567890][)]", "").length()) / 2;
        do {
            result = Integer.parseInt(input.ask(mainMenu));
        } while (result < 1 || result > menuCount);
        return result;
    }
}
