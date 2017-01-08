package ru.job4j;

/**
 * Bishop class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class Bishop extends Figure {
    /**
     * Constructor.
     * @param position cell.
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Move figure to destination cell.
     * @param dest destination.
     * @param cells array of cell.
     * @return if movement possible - array of cells that figure go.
     * @throws ImpossibleMoveException if movement not possible.
     */
    public Cell[] way(Cell dest, Cell[][] cells) throws ImpossibleMoveException {
        return new Cell[0];
    }
}
