package ru.job4j;

/**
 * Input class.
 *
 * @author Denis
 * @since 19.02.2017
 */
public interface Input {
    /**
     * Ask int.
     *
     * @param question to ask.
     * @return answer.
     */
    int ask(String question);

    /**
     * Ask odd int.
     *
     * @param question to ask.
     * @return answer.
     */
    int askOdd(String question);

    /**
     * Ask int in range.
     *
     * @param question .
     * @param range    range of int.
     * @return user choose.
     */
    int ask(String question, int[] range);
}
