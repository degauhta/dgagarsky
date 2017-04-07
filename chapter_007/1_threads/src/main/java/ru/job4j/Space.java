package ru.job4j;

import java.util.StringTokenizer;

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
     * Thread words.
     */
    private Thread threadWords;

    /**
     * Thread spaces.
     */
    private Thread threadSpaces;

    /**
     * Count words and spaces in sentence.
     *
     * @param sentence sentence
     * @param interrupt interruption flag
     * @param pause pause flag
     * @throws InterruptedException error
     */
    void count(String sentence, boolean interrupt, boolean pause) throws InterruptedException {
        System.out.println("start");
        long start = System.currentTimeMillis();
        if (pause) {
            Thread.sleep(1100);
        }
        this.threadWords = new Thread(new Calculate(sentence, true), "threadWords");
        this.threadSpaces = new Thread(new Calculate(sentence, false), "threadSpaces");
        this.threadWords.start();
        this.threadSpaces.start();
        if (interrupt) {
            stop(this.threadWords);
            stop(this.threadSpaces);
        } else {
            threadWords.join();
            threadSpaces.join();
        }
        while (this.threadWords.isAlive() & this.threadSpaces.isAlive()) {
            if (System.currentTimeMillis() - start > 1000) {
                stop(this.threadWords);
                stop(this.threadSpaces);
            }
        }
        System.out.println("finish");
    }

    /**
     * Stop current thread.
     *
     * @param current thread to stop
     * @throws InterruptedException error
     */
    private void stop(Thread current) throws InterruptedException {
        current.interrupt();
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
         * @param sentence   sentence
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
            if (!threadWords.isInterrupted() && this.countWords) {
                words = this.sentence.split("\\s+").length;
                System.out.println(String.format("Words count %s", words));
            } else if (!threadSpaces.isInterrupted() && !this.countWords) {
                spaces = new StringTokenizer(this.sentence, " ").countTokens() - 1;
                System.out.println(String.format("Spaces count %s", spaces));
            }
        }
    }
}