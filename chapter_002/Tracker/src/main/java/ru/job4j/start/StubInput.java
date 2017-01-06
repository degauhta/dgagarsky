package ru.job4j.start;

/**
 * Stub input class.
 *
 * @author Denis
 * @since 26.12.2016
 */
public class StubInput implements Input {
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
    public StubInput(String[] answers) {
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
}
