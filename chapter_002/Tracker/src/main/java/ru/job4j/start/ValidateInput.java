package ru.job4j.start;

/**
 * ValidateInput class.
 *
 * @author Denis
 * @since 02.01.2017
 */
public class ValidateInput extends ConsoleInput {
    /**
     *
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
