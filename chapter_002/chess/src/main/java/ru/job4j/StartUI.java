package ru.job4j;

/**
 * StartUI class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class StartUI {
    /**
     * Board.
     */
    private Board board;

    /**
     * Start.
     * @param board chess.
     */
    public void startUI(Board board) {
        this.board = new Board();
    }

    /**
     * Main method.
     * @param args array of parameters.
     */
    public static void main(String[] args) {
        Board board = new Board();
        new StartUI().startUI(board);
        board.move(board.getCell(1, 'a'), board.getCell(3, 'a'));
    }
}
