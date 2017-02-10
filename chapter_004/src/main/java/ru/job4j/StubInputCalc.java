package ru.job4j;

/**
 * StubInputCalc class.
 *
 * @author Denis
 * @since 09.02.2017
 */
public class StubInputCalc implements InputCalc {
    /**
     * Array of answers.
     */
    private String[] answers;
    /**
     * Iterator.
     */
    private int position;

    /**
     * Constructor.
     * @param answers array.
     */
    public StubInputCalc(String[] answers) {
        this.answers = answers;
    }

    /**
     * Stub question.
     * @param question question.
     * @return answer.
     */
    public String ask(String question) {
        return this.answers[position++];
    }

    /**
     * Select menu item.
     * @param question .
     * @param range of menu items.
     * @return choose.
     */
    public int ask(String question, int[] range) {
        //throw new UnsupportedOperationException("Unsup operation");
        return Integer.valueOf(this.answers[position++]);
    }

    /**
     * Ask double.
     *
     * @param question question.
     * @return user choose.
     */
    @Override
    public double askDouble(String question) throws NumberFormatException {
        return Double.parseDouble(this.answers[position++]);
    }
}
