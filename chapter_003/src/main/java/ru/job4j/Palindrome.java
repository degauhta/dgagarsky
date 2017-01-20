package ru.job4j;

/**
 * Palindrome class.
 *
 * @author Denis
 * @since 20.01.2017
 */
public class Palindrome {
    /**
     * Check string for palindrome.
     * @param s string.
     * @return result of check.
     */
    public boolean isInputPalindrome(String s) {
        boolean result = true;
        s = s.toLowerCase();
        for (int i = 0; i < 3; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
