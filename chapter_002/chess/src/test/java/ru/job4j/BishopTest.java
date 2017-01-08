package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BishopTest class.
 *
 * @author Denis
 * @since 07.01.2017
 */
public class BishopTest {
    /**
     * Test possible move.
     * @throws ImpossibleMoveException if movement not possible.
     */
    @Test
    public void whenPossibleMoveThenReturnArray() throws ImpossibleMoveException {
        Figure figure = new Bishop(new Cell(1, 'a'));
        assertThat(figure.way(null, null), is(new Cell[0]));
    }

    //todo test for exception
}