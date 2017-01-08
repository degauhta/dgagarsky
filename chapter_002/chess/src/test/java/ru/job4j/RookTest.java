package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * RookTest class.
 *
 * @author Denis
 * @since 07.01.2017
 */
public class RookTest {
    /**
     * Cells array.
     */
    private Cell[][] cells;

    /**
     * Init.
     */
    @Before
    public void init() {
        cells = new Cell[8][8];
        int index = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = 97; j < 105; j++) {
                cells[i - 1][index++] = new Cell(i, (char) j);
            }
            index = 0;
        }
    }

    /**
     * Test rank positive move.
     */
    @Test
    public void whenOnlyRanksMovingPositiveThenReturnArray() {
        Figure figure = new Rook(new Cell(2, 'a'));
        Cell[] expected = new Cell[2];
        expected[0] = cells[2][0];
        expected[1] = cells[3][0];
        assertThat(figure.way(new Cell(5, 'a'), cells), is(expected));
    }

    /**
     * Test rank negative move.
     */
    @Test
    public void whenOnlyRanksMovingNegativeThenReturnArray() {
        Figure figure = new Rook(new Cell(4, 'a'));
        Cell[] expected = new Cell[2];
        expected[0] = cells[2][0];
        expected[1] = cells[1][0];
        assertThat(figure.way(new Cell(1, 'a'), cells), is(expected));
    }

    /**
     * Test file positive move.
     */
    @Test
    public void whenOnlyFileMovingPositiveThenReturnArray() {
        Figure figure = new Rook(new Cell(2, 'a'));
        Cell[] expected = new Cell[2];
        expected[0] = cells[1][1];
        expected[1] = cells[1][2];
        assertThat(figure.way(new Cell(2, 'd'), cells), is(expected));
    }

    /**
     * Test file negative move.
     */
    @Test
    public void whenOnlyFileMovingNegativeThenReturnArray() {
        Figure figure = new Rook(new Cell(2, 'd'));
        Cell[] expected = new Cell[2];
        expected[0] = cells[1][2];
        expected[1] = cells[1][1];
        assertThat(figure.way(new Cell(2, 'a'), cells), is(expected));
    }

    /**
     * Test impossible move.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenNotPossibleMoveThenImpossibleMoveException() {
        Figure figure = new Rook(new Cell(1, 'a'));
        assertThat(figure.way(new Cell(2, 'b'), cells), is(new Cell[0]));
    }
}