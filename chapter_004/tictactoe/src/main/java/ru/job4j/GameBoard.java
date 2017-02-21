package ru.job4j;

/**
 * GameBoard class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public class GameBoard implements Board {
    /**
     * Reset console color.
     */
    private static final String ANSI_RESET = "\u001B[0m";
    /**
     * Console console red.
     */
    private static final String ANSI_RED = "\u001B[31m";
    /**
     * Console color green.
     */
    private static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Array of cells.
     */
    private char[][] cells;

    /**
     * Board size.
     */
    private final int size;

    /**
     * Main constructor.
     *
     * @param size board size.
     */
    public GameBoard(int size) {
        this.size = size;
        this.cells = new char[size][size];
        clear();
    }

    /**
     * Clear board.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.cells[i][j] = '\u0000';
            }
        }
    }

    /**
     * Put sign on board.
     *
     * @param row    row.
     * @param column column.
     * @param sign   sign.
     * @return true in case of success.
     */
    @Override
    public boolean put(int row, int column, char sign) {
        boolean result = false;
        if (this.cells[row][column] == '\u0000') {
            this.cells[row][column] = sign;
            result = true;
        }
        return result;
    }

    /**
     * Show board.
     */
    @Override
    public void show() {
        final String line = new String(new char[size * 4 - 1]).replace("\0", "-");
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (j != 0) {
                    System.out.print("|");
                }
                if (this.cells[i][j] == 'X') {
                    System.out.printf(ANSI_RED + " %s ", this.cells[i][j] + ANSI_RESET);
                } else if (this.cells[i][j] == 'O') {
                    System.out.printf(ANSI_GREEN + " %s ", this.cells[i][j] + ANSI_RESET);
                } else {
                    System.out.printf("   ");
                }
            }
            if (i != this.size - 1) {
                System.out.println();
                System.out.println(line);
            }
        }
        System.out.println();
    }

    /**
     * Checking end of game.
     *
     * @param sign sign.
     * @return true if game is over.
     */
    public boolean isGameOver(char sign) {
        boolean result = false;
        int countRow = 0;
        int countCol = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (cells[i][j] == sign) {
                    countRow++;
                } else {
                    countRow = 0;
                }
                if (cells[j][i] == sign) {
                    countCol++;
                } else {
                    countCol = 0;
                }
            }
            if (countCol == this.size || countRow == this.size) {
                result = true;
                break;
            }
        }
        int countDiagonal = 0;
        if (!result) {
            int j = 0;
            for (int i = 0; i < this.size; i++) {
                if (cells[i][j] == sign) {
                    countDiagonal++;
                } else {
                    countDiagonal = 0;
                }
                j++;
            }
        }
        result = result || countDiagonal == this.size;
        if (!result) {
            countDiagonal = 0;
            int j = 0;
            for (int i = this.size - 1; i >= 0; i--) {
                if (cells[i][j] == sign) {
                    countDiagonal++;
                } else {
                    countDiagonal = 0;
                }
                j++;
            }
        }
        return result || countDiagonal == this.size;
    }

    /**
     * Get size of board.
     *
     * @return size.
     */
    public int getSize() {
        return this.size;
    }
}
