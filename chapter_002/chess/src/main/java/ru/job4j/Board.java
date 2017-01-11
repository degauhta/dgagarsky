package ru.job4j;

/**
 * Board class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class Board {
    /**
     * All chess figures.
     */
    private Figure[] figures;
    /**
     * Board cells.
     */
    private Cell[][] cells = new Cell[8][8];

    /**
     * Constructor.
     */
    public Board() {
        int index = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = 97; j < 105; j++) {
                cells[i - 1][index++] = new Cell(i, (char) j);
            }
            index = 0;
        }

        Figure[] figures = new Figure[32];
        figures[index++] = new Rook(this.getCell(1, 'a'));
        for (int i = 1; i < 9; i++) {
            figures[index++] = new Pawn(this.getCell(2, (char) (96 + i)));
        }
        this.figures = figures;
    }

    /**
     * Get cell.
     * @param rank number.
     * @param file char.
     * @return cell.
     */
    public Cell getCell(int rank, char file) {
        return cells[rank - 1][file - 97];
    }

    /**
     * Move figure.
     * @param source cell.
     * @param dest destination cell.
     * @return true if
     * @throws ImpossibleMoveException if figure cant movement in dest cell.
     * @throws OccupiedWayException if dest cell is occupied.
     * @throws FigureNotFoundException if in source cell no figure.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException,
            OccupiedWayException, FigureNotFoundException {
        boolean figureNotFound = true;
        int current = -1;
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null && figures[i].getPosition().compareCells(source)) {
                figureNotFound = false;
                current = i;
                break;
            }
        }
        if (figureNotFound) {
            throw new FigureNotFoundException("Figure not found.");
        }

        Cell[] way = figures[current].way(dest, cells);

        //todo if for knight
        boolean occupiedWay = false;
        for (Cell cellInTheWay : way) {
            for (Figure figure : figures) {
                if (figure != null && figure.getPosition().compareCells(cellInTheWay)) {
                    occupiedWay = true;
                    break;
                }
            }
        }
        if (occupiedWay) {
            throw new OccupiedWayException("Occupied way.");
        }

        figures[current] = figures[current].clone(dest);

        return true;
    }
}