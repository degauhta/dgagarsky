package ru.job4j;

/**
 * Figure abstract class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public abstract class Figure {
    /**
     * Position of figure.
     */
    private final Cell position;

    /**
     * Constructor.
     * @param position of figure.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Move figure to destination cell.
     * @param dest destination.
     * @param cells array of cell.
     * @return if movement possible - array of cells that figure go.
     * @throws ImpossibleMoveException if movement not possible.
     */
    public abstract Cell[] way(Cell dest, Cell[][] cells) throws ImpossibleMoveException;

    /**
     * Clone figure to square.
     * @param dest destination.
     * @return clone figure.
     */
    public abstract Figure clone(Cell dest);

    /**
     * Get position of figure.
     * @return position.
     */
    public Cell getPosition() {
        return this.position;
    }
}
