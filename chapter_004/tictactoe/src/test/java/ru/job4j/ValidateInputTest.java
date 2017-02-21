package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ValidateInputTest class.
 *
 * @author Denis
 * @since 20.02.2017
 */
public class ValidateInputTest {
    /**
     * Ask int.
     * In 1.
     */
    @Test
    public void whenAskIntAndGiveIntThenOk() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Input input = new ValidateInput();
        input.ask("test");
        String expected = Joiner.on(System.lineSeparator()).join("test", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Ask int.
     * In q and 1.
     */
    @Test
    public void whenAskIntAndGiveStringAndIntThenReturnMessageAndOk() {
        String actual = Joiner.on(System.lineSeparator()).join("q", "1");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(actual.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Input input = new ValidateInput();
        input.ask("test");
        String expected = Joiner.on(System.lineSeparator()).join("test", "Number is - validate data.", "test", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Ask int contained in array.
     * In int contained in array.
     */
    @Test
    public void whenAskIntContainedInArrayAndGiveThatIntThenOK() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
        int[] array = new int[]{0, 1, 2};
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Input input = new ValidateInput();
        input.ask("test", array);
        String expected = Joiner.on(System.lineSeparator()).join("test", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Ask int contained in array.
     * In string and int contained in array.
     */
    @Test
    public void whenAskIntContainedInArrayAndGiveStringThanThatIntThenMessageAndOK() {
        String actual = Joiner.on(System.lineSeparator()).join("q", "1");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(actual.getBytes());
        int[] array = new int[]{0, 1, 2};
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Input input = new ValidateInput();
        input.ask("test", array);
        String expected = Joiner.on(System.lineSeparator()).join("test", "Number is - validate data.", "test", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Ask int contained in array.
     * In int and int contained in array.
     */
    @Test
    public void whenAskIntContainedInArrayAndGiveIntAndThanThatIntThenMessageAndOK() {
        String actual = Joiner.on(System.lineSeparator()).join("123", "1");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(actual.getBytes());
        int[] array = new int[]{0, 1, 2};
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Input input = new ValidateInput();
        input.ask("test", array);
        String expected = Joiner.on(System.lineSeparator()).join("test", "Enter correct number 0-2", "test", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Ask odd number.
     */
    @Test
    public void whenAskOddNumberBiggerOneAndGiveOneThenOK() {
        String actual = Joiner.on(System.lineSeparator()).join("1", "q", "2", "5");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(actual.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Input input = new ValidateInput();
        input.askOdd("odd number > 1");
        String expected = Joiner.on(System.lineSeparator()).join("odd number > 1",
                "odd number > 1", "Number is - validate data.", "odd number > 1", "odd number > 1", "");
        assertThat(outputStream.toString(), is(expected));
    }
}
