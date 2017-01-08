package ru.job4j;

import org.junit.Test;

//import static org.junit.Assert.assertThat;

/**
 * BoardTest class.
 *
 * @author Denis
 * @since 07.01.2017
 */
public class BoardTest {
    /**
     * Test figure not found.
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenFigureNotFoundThenException() {
        Board board = new Board();
        StartUI startUI = new StartUI();
        startUI.startUI(board);
        board.move(board.getCell(8, 'a'), board.getCell(3, 'a'));
    }

    /**
     * Test occupied way.
     */
    @Test(expected = OccupiedWayException.class)
    public void whenOccupiedWayThenException() {
        Board board = new Board();
        StartUI startUI = new StartUI();
        startUI.startUI(board);
        board.move(board.getCell(1, 'a'), board.getCell(3, 'a'));
    }
}