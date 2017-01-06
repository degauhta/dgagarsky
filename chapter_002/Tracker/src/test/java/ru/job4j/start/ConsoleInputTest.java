package ru.job4j.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ConsoleInputTest class.
 *
 * @author Denis
 * @since 27.12.2016
 */
public class ConsoleInputTest {
    /**
     * Testing console input.
     */
    @Test
    public void ask() {
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        assertThat(consoleInput.ask("123"), is("123"));
    }

    /**
     * Test MenuOutException.
     */
    @Test(expected = MenuOutException.class)
    public void whenWrongNumberThenMenuOutException() {
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        consoleInput.ask("question", new int[]{0, 1, 2});
    }
}