package ru.job4j;

import ru.job4j.start.MenuOutException;

/**
 * ValidateInputCalc class.
 *
 * @author Denis
 * @since 08.02.2017
 */
public class ValidateInputCalc extends ConsoleInputCalc {
    /**
     * Ask double.
     * @param question question.
     * @return user choose.
     */
    public double askDouble(String question) {
        double result = 0.0;
        boolean invalid = true;
        do {
            try {
                result = super.askDouble(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Number is - validate data.");
            }
        } while (invalid);
        return result;
    }

    /**
     * Ask menu.
     * @param question .
     * @param range of menu items.
     * @return user choose.
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Select correct number.");
            } catch (NumberFormatException nfe) {
                System.out.println("Number is - validate data.");
            }
        } while (invalid);
        return value;
    }
}
