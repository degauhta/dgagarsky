package ru.job4j;

/**
 * Rook class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class Rook extends Figure {
    /**
     * Constructor.
     *
     * @param position of figure.
     */
    public Rook(Cell position) {
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
        if (this.getPosition().getFile() == dest.getFile()
                && this.getPosition().getRank() != dest.getRank()) {
            int rankLength = dest.getRank() - this.getPosition().getRank();
            boolean rankPositiveWay = rankLength > 0;
            rankLength = Math.abs(rankLength) - 1;
            Cell[] result = new Cell[rankLength];
            for (int i = 0; i < rankLength; i++) {
                if (rankPositiveWay) {
                    result[i] = cells[this.getPosition().getRank() + i][this.getPosition().getFile() - 97];
                } else {
                    result[i] = cells[this.getPosition().getRank() - i - 2][this.getPosition().getFile() - 97];
                }
            }
            return result;

        } else if (this.getPosition().getFile() != dest.getFile()
                && this.getPosition().getRank() == dest.getRank()) {
            int fileLength = dest.getFile() - this.getPosition().getFile();
            boolean filePositiveWay = fileLength > 0;
            fileLength = Math.abs(fileLength) - 1;
            Cell[] result = new Cell[fileLength];
            for (int i = 0; i < fileLength; i++) {
                if (filePositiveWay) {
                    result[i] = cells[this.getPosition().getRank() - 1][this.getPosition().getFile() - 96 + i];
                } else {
                    result[i] = cells[this.getPosition().getRank() - 1][this.getPosition().getFile() - 96 - i - 2];
                }
            }
            return result;
        }
        throw new ImpossibleMoveException("ImpossibleMoveException");
    }
}
