package ru.job4j;

/**
 * Parentheses class.
 *
 * @author Denis
 * @since 12.01.2017
 */
public class Parentheses {

    /**
     * Check for balance parentheses in formula.
     * @param formula checking expression.
     * @return result of check.
     */
    public boolean checkParenthesesBalance(String formula) {
        int count = 0;
        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') {
                count++;
            } else if (formula.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                break;
            }
        }
        return count == 0;
    }

}
