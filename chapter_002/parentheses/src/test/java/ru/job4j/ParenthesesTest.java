package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Parentheses test class.
 *
 * @author Denis
 * @since 12.01.2017
 */
public class ParenthesesTest {
    /**
     * Test formula.
     */
    @Test
    public void whenRightFormulaThenTrue() {
        Parentheses parentheses = new Parentheses();
        assertThat(parentheses.checkParenthesesBalance("((()))"), is(true));
    }

    /**
     * Test wrong formula.
     */
    @Test
    public void whenWrongFormulaThenFalse() {
        Parentheses parentheses = new Parentheses();
        assertThat(parentheses.checkParenthesesBalance(")((()))"), is(false));
        assertThat(parentheses.checkParenthesesBalance("(("), is(false));
        assertThat(parentheses.checkParenthesesBalance("())"), is(false));
    }
}