package ru.job4j;

/**
 * Blockable class.
 *
 * @author Denis
 * @since 20.05.2017
 */
public interface Blockable {
    /**
     * Get block flag.
     *
     * @return block flag
     */
    boolean isBlocking();

    /**
     * Get position.
     *
     * @return cell
     */
    Cell getPosition();
}
