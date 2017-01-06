package ru.job4j.start;

/**
 * BaseAction class.
 *
 * @author Denis
 * @since 06.01.2017
 */
public abstract class BaseAction implements UserAction {
    /**
     * Name (description) of the action.
     */
    private String name;

    /**
     * Constructor.
     * @param name of the action.
     */
    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * Menu unique key.
     *
     * @return key.
     */
    public abstract int key();

    /**
     * Execute action of tracker.
     *
     * @param input   input.
     * @param tracker tracker.
     */
    public abstract void execute(Input input, Tracker tracker);

    /**
    * Menu desc.
    *
    * @return description.
    */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}
