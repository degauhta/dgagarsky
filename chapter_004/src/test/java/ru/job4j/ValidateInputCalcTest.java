package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ValidateInputCalcTest class.
 *
 * @author Denis
 * @since 09.02.2017
 */
public class ValidateInputCalcTest {
    /**
     * Test number. Input number.
     */
    @Test
    public void whenInputNumberThenReturnNumber() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ValidateInputCalc validateInput = new ValidateInputCalc();
        assertThat(validateInput.askDouble("question"), is(1.0));
    }

    /**
     * Test number. Input not number.
     */
    @Test
    public void whenInputNotParsableNumberThenReturnMessage() {
        String input = Joiner.on(System.lineSeparator()).join("qwerty", "1");
        String expected = Joiner.on(System.lineSeparator()).join("",
                "Number is - validate data.", "", "");
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ValidateInputCalc validateInput = new ValidateInputCalc();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        assertThat(validateInput.askDouble(""), is(1.0));
        assertThat(stream.toString(), is(expected));
    }

    /**
     * Test menu. Input valid menu number.
     */
    @Test
    public void whenInputValidNumberThenReturnNumber() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ValidateInputCalc validateInput = new ValidateInputCalc();
        assertThat(validateInput.ask("question", new int[]{0, 1, 2}), is(1));
    }

    /**
     * Test menu. Input not valid number.
     */
    @Test
    public void whenInputNotValidNumberThenReturnMessage() {
        String input = Joiner.on(System.lineSeparator()).join("33", "1");
        String expected = Joiner.on(System.lineSeparator()).join("",
                "Select correct number.", "", "");
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        ValidateInputCalc validateInput = new ValidateInputCalc();
        assertThat(validateInput.ask("", new int[]{0, 1, 2}), is(1));
        assertThat(stream.toString(), is(expected));
    }

    /**
     * Test menu. Input not number.
     */
    @Test
    public void whenInputNotNumberThenReturnMessage() {
        String input = Joiner.on(System.lineSeparator()).join("qwerty", "1");
        String expected = Joiner.on(System.lineSeparator()).join("",
                "Number is - validate data.", "", "");
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        ValidateInputCalc validateInput = new ValidateInputCalc();
        assertThat(validateInput.ask("", new int[]{0, 1, 2}), is(1));
        assertThat(stream.toString(), is(expected));
    }
}