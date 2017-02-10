package ru.job4j;

/**
 * UserAction class.
 *
 * @author Denis
 * @since 30.12.2016
 */
public interface UserActionCalc {
    /**
     * Key of operation.
     * @return key.
     */
    int key();

    /**
     * Execute operation.
     * @param input input.
     * @param calculator tracker.
     */
    void execute(InputCalc input, Calculator calculator);

    /**
     * Print menu name of operation.
     * @return name.
     */
    String info();

}
