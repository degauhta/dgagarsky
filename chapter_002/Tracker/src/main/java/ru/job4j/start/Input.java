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
}
