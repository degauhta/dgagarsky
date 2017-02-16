package ru.job4j;

/**
 * Action class.
 *
 * @author Denis
 * @since 16.02.2017
 */
public interface Action {
    /**
     * Execute.
     *
     * @param key menu key.
     */
    void execute(int key);
}
