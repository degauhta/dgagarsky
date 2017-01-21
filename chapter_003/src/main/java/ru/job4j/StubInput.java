package ru.job4j;

/**
 * StubInput class.
 *
 * @author Denis
 * @since 21.01.2017
 */
class StubInput implements Input {
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
     * @param answers array of chat answers.
     */
    StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Chat phrase.
     *
     * @return answer.
     */
    @Override
    public String chat() {
        return this.answers[position++];
    }
}
