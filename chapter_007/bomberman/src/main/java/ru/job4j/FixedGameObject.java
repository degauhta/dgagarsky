package ru.job4j;

/**
 * FixedGameObject class.
 *
 * @author Denis
 * @since 12.05.2017
 */
abstract class FixedGameObject implements Blockable {
    /**
     * Cell.
     */
    private Cell position;

    /**
     * Blocking flag.
     */
    private final boolean blocking;

    /**
     * Default constructor.
     *
     * @param position cell
     * @param blocking block flag
     */
    FixedGameObject(Cell position, boolean blocking) {
        this.position = position;
        this.blocking = blocking;
    }

    /**
     * Get block flag.
     *
     * @return block flag
     */
    public boolean isBlocking() {
        return blocking;
    }

    /**
     * Get position.
     *
     * @return cell
     */
    public Cell getPosition() {
        return position;
    }
}
