package ru.job4j;

import ru.job4j.start.Tracker;
import ru.job4j.start.MenuTracker;
import ru.job4j.start.Input;
import ru.job4j.start.ValidateInput;

/**
 * StartUIDB class.
 *
 * @author Denis
 * @since 01.07.2017
 */
class StartUIDB {
    /**
     * Console input.
     */
    private Input input;
    /**
     * TrackerInMemory.
     */
    private Tracker tracker;

    /**
     * Default constructor.
     *
     * @param input   console input.
     * @param tracker tracker.
     */
    private StartUIDB(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Main method.
     *
     * @param args - args.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new TrackerDB();
        new StartUIDB(input, tracker).init();
    }

    /**
     * Initialization.
     */
    private void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        int[] menuRange = menuTracker.fillAction();
        do {
            menuTracker.show();
            menuTracker.select(input.ask("Select: ", menuRange));
        } while (!"y".equals(this.input.ask("Exit? (y):")));
    }
}