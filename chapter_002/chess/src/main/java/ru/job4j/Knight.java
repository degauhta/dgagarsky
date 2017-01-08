package ru.job4j;

/**
 * Knight class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class Knight extends Figure {
    /**
     * Constructor.
     * @param position of figure.
     */
    public Knight(Cell position) {
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
