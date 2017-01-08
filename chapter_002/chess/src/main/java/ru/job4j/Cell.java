package ru.job4j;

/**
 * Cell class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class Cell {
    /**
     * Ranks numbers (1 to 8).
     */
    private int rank;
    /**
     * Files letters (a to h).
     */
    private char file;

    /**
     * Get rank number of cell.
     * @return rank.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Get file letter of cell.
     * @return letter.
     */
    public char getFile() {
        return file;
    }

    /**
     * Constructor.
     * @param rank number.
     * @param file letter.
     */
    public Cell(int rank, char file) {
        this.rank = rank;
        this.file = file;
    }

    /**
     * Equals cells.
     * @param cell to compare.
     * @return result.
     */
    public boolean compareCells(Cell cell) {
        return this.getFile() == cell.getFile() && this.getRank() == cell.getRank();
    }
}
