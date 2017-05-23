package ru.job4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ActiveGameObject class.
 *
 * @author Denis
 * @since 15.05.2017
 */
abstract class ActiveGameObject implements Blockable {
    /**
     * Cell.
     */
    private Cell position;

    /**
     * Move flag.
     */
    private AtomicBoolean timeToMove;

    /**
     * Field.
     */
    private Field field;

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
    ActiveGameObject(Cell position, boolean blocking) {
        this.position = position;
        this.timeToMove = new AtomicBoolean(false);
        this.blocking = blocking;
    }

    /**
     * Get flag to move.
     *
     * @return flag
     */
    AtomicBoolean getTimeToMove() {
        return timeToMove;
    }

    /**
     * Set filed.
     *
     * @param field field
     */
    void setField(Field field) {
        this.field = field;
    }

    /**
     * Get field.
     *
     * @return field
     */
    Field getField() {
        return field;
    }

    /**
     * Get position.
     *
     * @return cell
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Set position.
     *
     * @param position cell
     */
    void setPosition(Cell position) {
        this.position = position;
    }

    /**
     * Set time to move.
     */
    void setTimeToMove() {
        this.timeToMove.set(true);
    }

    /**
     * Get block flag.
     *
     * @return block flag
     */
    public boolean isBlocking() {
        return blocking;
    }
}