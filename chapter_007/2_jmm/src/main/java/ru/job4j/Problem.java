package ru.job4j;

/**
 * Problem class.
 *
 * @author Denis
 * @since 08.04.2017
 */
class Problem {
    /**
     * Count loop.
     */
    private int loops;

    /**
     * Shared variable.
     */
    private int shared;

    /**
     * Default constructor.
     *
     * @param loops loops
     */
    Problem(int loops) {
        this.loops = loops;
    }

    /**
     * Counter.
     */
    void counter() {
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get shared variable.
     *
     * @return shared variable
     */
    int getShared() {
        return shared;
    }

    /**
     * MyRunnable class.
     */
    private class MyRunnable implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see     java.lang.Thread#run()
         */
        @Override
        public void run() {
            increase();
        }

        /**
         * Increase shared variable.
         */
        private void increase() {
            for (int i = 0; i < loops; i++) {
                shared++;
            }
        }
    }
}
