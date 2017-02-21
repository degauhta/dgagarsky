package ru.job4j;

/**
 * Game class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public interface Game {
    /**
     * Start party.
     */
    void party();

    /**
     * AI move.
     *
     * @param sign sign to put.
     */
    void aiMove(char sign);

    /**
     * Get result.
     *
     * @return result.
     */
    StringBuilder getResult();
}
