package ru.job4j.start;

/**
 * UserAction class.
 *
 * @author Denis
 * @since 30.12.2016
 */
public interface UserAction {
    /**
     * Key of operation.
     * @return key.
     */
    int key();

    /**
     * Execute operation.
     * @param input input.
     * @param tracker tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Print menu name of operation.
     * @return name.
     */
    String info();

}
