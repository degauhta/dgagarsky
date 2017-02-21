package ru.job4j;

/**
 * GameSeries class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public class GameSeries implements Game {
    /**
     * GameSeries result.
     */
    private StringBuilder result;

    /**
     * User put sign first flag.
     */
    private final boolean userPutFirst;

    /**
     * Board.
     */
    private Board board;

    /**
     * Count of parties.
     */
    private int countMatches;

    /**
     * Input.
     */
    private Input input;

    /**
     * Main constructor.
     *
     * @param input        input.
     * @param userPutFirst userPutFirst.
     * @param board        board.
     * @param countMatches countMathes.
     */
    public GameSeries(Input input, boolean userPutFirst, Board board, int countMatches) {
        this.input = input;
        this.userPutFirst = userPutFirst;
        this.board = board;
        this.countMatches = countMatches;
        this.result = new StringBuilder();
    }

    /**
     * Start party.
     */
    @Override
    public void party() {
        int[] range = new int[board.getSize()];
        int row;
        int column;
        int cellsCount = board.getSize() * board.getSize();
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        char currentSign = 'X';
        boolean success;
        for (int z = 0; z < this.countMatches; z++) {
            board.clear();
            this.result.append(String.format("%s %s %s ", "Game", (z + 1), "-"));
            for (int i = 0; i < cellsCount; i++) {
                if ((this.userPutFirst & i % 2 == 0) || (!this.userPutFirst & i % 2 == 1)) {
                    System.out.println("Player move.");
                    do {
                        row = input.ask("enter row", range);
                        column = input.ask("enter column", range);
                        success = board.put(row, column, this.userPutFirst ? 'X' : 'O');
                        if (!success) {
                            System.out.println("This cell is busy");
                        }
                    } while (!success);
                } else {
                    System.out.println("PC move.");
                    aiMove(this.userPutFirst ? 'O' : 'X');
                }
                board.show();
                if (checkGameOver(cellsCount, currentSign, i)) {
                    break;
                }
                currentSign = currentSign == 'X' ? 'O' : 'X';
            }
        }
    }

    /**
     * Check end of match.
     * @param cellsCount count of cells in board.
     * @param currentSign current sign.
     * @param currentCell current cell.
     * @return true if match is over.
     */
    private boolean checkGameOver(int cellsCount, char currentSign, int currentCell) {
        boolean result = false;
        if (board.isGameOver(currentSign)) {
            if ((this.userPutFirst & currentCell % 2 == 0) || (!this.userPutFirst & currentCell % 2 == 1)) {
                this.result.append("player win.");
            } else {
                this.result.append("PC win.");
            }
            result = true;
        } else if (currentCell == cellsCount - 1) {
            this.result.append("draw.");
            result = true;
        }
        return result;
    }

    /**
     * AI move.
     *
     * @param sign sign to put.
     */
    @Override
    public void aiMove(char sign) {
        boolean success = false;
        for (int i = 0; i < board.getSize(); i++) {
            if (success) {
                break;
            }
            for (int j = 0; j < board.getSize(); j++) {
                if (board.put(i, j, sign)) {
                    success = true;
                    break;
                }
            }
        }
    }

    /**
     * Get result.
     *
     * @return result.
     */
    @Override
    public StringBuilder getResult() {
        return this.result;
    }
}
