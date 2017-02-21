package ru.job4j;

/**
 * StubInput class.
 *
 * @author Denis
 * @since 19.02.2017
 */
public class StubInput implements Input {
    /**
     * Array of answers.
     */
    private int[] answers;

    /**
     * Position of answer.
     */
    private int position;

    /**
     * Main constructor.
     *
     * @param answers stub answers.
     */
    public StubInput(int[] answers) {
        this.answers = answers;
        this.position = 0;
    }

    /**
     * Ask odd int.
     *
     * @param question to ask.
     * @return answer.
     */
    @Override
    public int askOdd(String question) {
        return answers[position++];
    }

    /**
     * Question to ask.
     *
     * @param question to ask.
     * @return answers.
     */
    @Override
    public int ask(String question) {
        return answers[position++];
    }

    /**
     * Select menu item.
     *
     * @param question .
     * @param range    of menu items.
     * @return user choose.
     */
    @Override
    public int ask(String question, int[] range) {
        return answers[position++];
    }
}
