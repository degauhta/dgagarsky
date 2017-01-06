package ru.job4j.start;

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
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Menu range.
     */
    private int[] menuRange;

    /**
     * Constructor.
     *
     * @param input console input.
     * @param tracker tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Main method.
     *
     * @param args - args.
     */
    public static void main(final String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

    /**
     * Initialization.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuRange = menuTracker.fillAction();
        do {
            menuTracker.show();
            menuTracker.select(input.ask("Select: ", menuRange));
        } while (!"y".equals(this.input.ask("Exit? (y):")));
    }
}