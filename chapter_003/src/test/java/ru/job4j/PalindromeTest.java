package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PalindromeTest class.
 *
 * @author Denis
 * @since 20.01.2017
 */
public class PalindromeTest {
    /**
     * String is palindrome.
     */
    @Test
    public void whenPalindromeThenTrue() {
        Palindrome palindrome = new Palindrome();
        assertThat(palindrome.isInputPalindrome("potop"), is(true));
        assertThat(palindrome.isInputPalindrome("POTOP"), is(true));
        assertThat(palindrome.isInputPalindrome("POTop"), is(true));
        assertThat(palindrome.isInputPalindrome("potOP"), is(true));
    }

    /**
     * Not Palindrome.
     */
    @Test
    public void whenNotPalindromeThenFalse() {
        Palindrome palindrome = new Palindrome();
        assertThat(palindrome.isInputPalindrome("potoq"), is(false));
        assertThat(palindrome.isInputPalindrome("PQTOP"), is(false));
        assertThat(palindrome.isInputPalindrome("POTqp"), is(false));
        assertThat(palindrome.isInputPalindrome("pqtOP"), is(false));
    }
}