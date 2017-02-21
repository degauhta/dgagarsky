package ru.job4j;

/**
 * InputRange class.
 *
 * @author Denis
 * @since 19.02.2017
 */
public interface InputRange {
    /**
     * Select menu item.
     * @param question .
     * @param range of menu items.
     * @return user choose.
     */
    int ask(String question, int[] range);
}
