package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * StartUITest class.
 *
 * @author Denis
 * @since 08.01.2017
 */
public class StartUITest {
    /**
     * test.
     */
    @Test
    public void whenThen() {
        Board board = new Board();
        StartUI startUI = new StartUI();
        startUI.startUI(board);
        assertThat(board.move(board.getCell(1, 'a'), board.getCell(1, 'c')), is(true));
    }
}