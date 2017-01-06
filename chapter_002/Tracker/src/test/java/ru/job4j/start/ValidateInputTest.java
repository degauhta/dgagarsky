package ru.job4j.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ValidateInputTest class.
 *
 * @author Denis
 * @since 02.01.2017
 */
public class ValidateInputTest {
    /**
     * Test validate input.
     */
    @Test
    public void ask() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ValidateInput validateInput = new ValidateInput();
        assertThat(validateInput.ask("question", new int[]{0, 1, 2}), is(1));
    }
}