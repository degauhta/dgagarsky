package ru.job4j;

/**
 * InputCalc class.
 *
 * @author Denis
 * @since 08.02.2017
 */
public interface InputCalc {
    /**
     * Ask double.
     * @param question question.
     * @return user choose.
     */
    double askDouble(String question);

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
