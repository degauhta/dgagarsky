package ru.job4j;

/**
 * Board class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public interface Board {
    /**
     * Show board.
     */
    void show();

    /**
     * Clear board.
     */
    void clear();

    /**
     * Put sign on board.
     *
     * @param row row.
     * @param column column.
     * @param sign sign.
     * @return true in case of success.
     */
    boolean put(int row, int column, char sign);

    /**
     * Checking end of game.
     *
     * @param sign sign.
     * @return true if game is over.
     */
    boolean isGameOver(char sign);

    /**
     * Get size of board.
     *
     * @return size.
     */
    int getSize();
}