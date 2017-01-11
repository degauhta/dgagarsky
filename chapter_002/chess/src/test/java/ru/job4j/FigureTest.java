package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * FigureTest class.
 *
 * @author Denis
 * @since 07.01.2017
 */
public class FigureTest {
    /**
     * Equals position of figure with source.
     */
    @Test
    public void whenCloneFigureThenTrue()  {
        Cell dest = new Cell(1, 'b');
        Figure figure = new Rook(new Cell(1, 'a'));
        assertThat(figure.clone(dest).getPosition().compareCells(dest), is(true));
    }
}