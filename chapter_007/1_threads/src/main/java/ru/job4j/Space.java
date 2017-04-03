package ru.job4j;

/**
 * Space class.
 *
 * @author Denis
 * @since 02.04.2017
 */
class Space {
    /**
     * Words count.
     */
    private int words;

    /**
     * Spaces count.
     */
    private int spaces;

    /**
     * Count words and spaces in sentence.
     *
     * @param sentence sentence
     * @throws InterruptedException error
     */
    void count(String sentence) throws InterruptedException {
        new Thread(new Calculate(sentence, true)).start();
        new Thread(new Calculate(sentence, false)).start();
        Thread.sleep(50L);
    }

    /**
     * Get words count.
     *
     * @return count
     */
    int getWords() {
        return words;
    }

    /**
     * Get spaces count.
     *
     * @return count
     */
    int getSpaces() {
        return spaces;
    }

    /**
     * Calculate words and space.
     */
    class Calculate implements Runnable {
        /**
         * Flag count words.
         */
        private boolean countWords;

        /**
         * Sentence.
         */
        private String sentence;

        /**
         * Main constructor.
         *
         * @param sentence sentence
         * @param countWords if true counts words, false - count spaces
         */
        Calculate(String sentence, boolean countWords) {
            this.sentence = sentence;
            this.countWords = countWords;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            if (this.countWords) {
                words = this.sentence.split("\\s+").length;
                System.out.println(String.format("Words count %s", words));
            } else {
                spaces = this.sentence.length() - this.sentence.replace(" ", "").length();
                System.out.println(String.format("Spaces count %s", spaces));
            }
        }
    }
}
