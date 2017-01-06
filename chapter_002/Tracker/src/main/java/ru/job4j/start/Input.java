package ru.job4j.start;

/**
 * Input interface.
 *
 * @author Denis
 * @since 27.12.2016
 */
public interface Input {
    /**
     * Question to ask.
     * @param question to ask.
     * @return answer.
     */
    String ask(String question);

    /**
     * Select menu item.
     * @param question .
     * @param range of menu items.
     * @return user choose.
     */
    int ask(String question, int[] range);
}
