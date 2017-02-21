package ru.job4j;

/**
 * ValidateInput class.
 *
 * @author Denis
 * @since 20.02.2017
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Ask int.
     *
     * @param question to ask.
     * @return answer.
     */
    @Override
    public int ask(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Number is - validate data.");
            }
        } while (invalid);
        return value;
    }

    /**
     * Ask odd int.
     *
     * @param question to ask.
     * @return answer.
     */
    @Override
    public int askOdd(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question);
                invalid = value % 2 == 0 | value <= 1;
            } catch (NumberFormatException nfe) {
                System.out.println("Number is - validate data.");
            }
        } while (invalid);
        return value;
    }

    /**
     * Ask int contained in array.
     *
     * @param question question.
     * @param range    of menu items.
     * @return user choose.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (OutOfArrayException moe) {
                System.out.println(String.format("%s %s-%s",
                        "Enter correct number", range[0], range[range.length - 1]));
            } catch (NumberFormatException nfe) {
                System.out.println("Number is - validate data.");
            }
        } while (invalid);
        return value;
    }
}
