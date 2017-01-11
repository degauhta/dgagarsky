package ru.job4j;

/**
 * Queen class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class Queen extends Figure {
    /**
     * Constructor.
     *
     * @param position of figure.
     */
    public Queen(Cell position) {
        super(position);
    }

    /**
     * Move figure to destination cell.
     *
     * @param dest destination.
     * @param cells array of cell.
     * @return if movement possible - array of cells that figure go.
     * @throws ImpossibleMoveException if movement not possible.
     */
    public Cell[] way(Cell dest, Cell[][] cells) throws ImpossibleMoveException {
        return new Cell[0];
    }

    /**
     * Clone figure to square.
     *
     * @param dest destination.
     * @return clone figure.
     */
    @Override
    public Figure clone(Cell dest) {
        return null;
    }
}
