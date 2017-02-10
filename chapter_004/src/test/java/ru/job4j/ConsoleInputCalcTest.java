package ru.job4j;

import org.junit.Test;
import ru.job4j.start.MenuOutException;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConsoleInputCalcTest class.
 *
 * @author Denis
 * @since 09.02.2017
 */
public class ConsoleInputCalcTest {
    /**
     * Testing console input string.
     */
    @Test
    public void whenInputStringThenReturnString() {
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        ConsoleInputCalc consoleInput = new ConsoleInputCalc();
        assertThat(consoleInput.ask("123"), is("123"));
    }

    /**
     * Testing console input double number.
     */
    @Test
    public void whenInputDoubleThenReturnDouble() {
        System.setIn(new ByteArrayInputStream("123.0".getBytes()));
        ConsoleInputCalc consoleInput = new ConsoleInputCalc();
        assertThat(consoleInput.askDouble("123.0"), is(123.0));
    }

    /**
     * Testing console input not double number.
     */
    @Test (expected = NumberFormatException.class)
    public void whenInputNotDoubleThenException() {
        System.setIn(new ByteArrayInputStream("qwer".getBytes()));
        ConsoleInputCalc consoleInput = new ConsoleInputCalc();
        consoleInput.askDouble("123.0");
    }

    /**
     * Testing menu input.
     */
    @Test
    public void whenChoseMenuOneThenReturnOne() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        int[] range = new int[] {0, 1, 2};
        ConsoleInputCalc consoleInput = new ConsoleInputCalc();
        assertThat(consoleInput.ask("012", range), is(1));
    }

    /**
     * Testing input wrong menu number.
     */
    @Test (expected = MenuOutException.class)
    public void whenChoseWrongNumberMenuThenReturnException() {
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        int[] range = new int[] {0, 1, 2};
        ConsoleInputCalc consoleInput = new ConsoleInputCalc();
        consoleInput.ask("012", range);
    }
}