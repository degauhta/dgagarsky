package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConsoleInputTest class.
 *
 * @author Denis
 * @since 21.01.2017
 */
public class ConsoleInputTest {
    /**
     * Testing console input.
     */
    @Test
    public void ask() {
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        assertThat(consoleInput.chat(), is("123"));
    }
}

