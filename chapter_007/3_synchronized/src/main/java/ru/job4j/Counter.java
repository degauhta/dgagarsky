package ru.job4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Counter class.
 *
 * @author Denis
 * @since 11.04.2017
 */
class Counter {
    /**
     * Count loop.
     */
    private int loops;

    /**
     * Shared variable.
     */
    private int shared;

    /**
     * Static shared variable.
     */
    private static int sharedStatic;

    /**
     * Atomic shared variable.
     */
    private AtomicInteger sharerAtomicInteger;

    /**
     * Default constructor.
     *
     * @param loops loops
     */
    Counter(int loops) {
        this.loops = loops;
        sharedStatic = 0;
        this.sharerAtomicInteger = new AtomicInteger();
    }

    /**
     * Count shared variable in two threads.
     *
     * @param method synchronized method flag
     * @param staticMethod synchronized static method flag
     * @param block synchronized block flag
     */
    void count(boolean method, boolean staticMethod, boolean block) {
        MySynchronized mySynchronized1 = new MySynchronized(method, staticMethod, block);
        MySynchronized mySynchronized2 = new MySynchronized(method, staticMethod, block);
        Thread t1 = new Thread(mySynchronized1);
        Thread t2 = new Thread(mySynchronized2);
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
     * Increase shared variable.
     * Method synchronized.
     */
    private synchronized void increase() {
        for (int i = 0; i < this.loops; i++) {
            this.shared++;
        }
    }

    /**
     * Increase shared variable.
     * Static method synchronized.
     *
     * @param loops loops
     */
    private static synchronized void increaseStatic(int loops) {
        for (int i = 0; i < loops; i++) {
            sharedStatic++;
        }
    }

    /**
     * Increase shared variable.
     * Block synchronized.
     */
    private void increaseBlock() {
        synchronized (this) {
            for (int i = 0; i < this.loops; i++) {
                this.shared++;
            }
        }
    }

    /**
     * Increase atomic variable.
     *
     */
    private void increaseAtomic() {
        for (int i = 0; i < this.loops; i++) {
            this.sharerAtomicInteger.incrementAndGet();
        }
    }

    /**
     * Get shared variable.
     *
     * @return shared variable
     */
    synchronized int getShared() {
        return this.shared;
    }

    /**
     * Get static variable.
     *
     * @return variable
     */
    static synchronized int getSharedStatic() {
        return sharedStatic;
    }

    /**
     * Get atomic variable.
     *
     * @return variable
     */
    int getSharerAtomicInteger() {
        return sharerAtomicInteger.get();
    }

    /**
     * My synchronized class.
     */
    private class MySynchronized implements Runnable {
        /**
         * Synchronized method flag.
         */
        private boolean method;

        /**
         * Synchronized static method flag.
         */
        private boolean staticMethod;

        /**
         * Synchronized block flag.
         */
        private boolean block;

        /**
         * Default constructor.
         *
         * @param method synchronized method flag
         * @param staticMethod synchronized static method flag
         * @param block synchronized block flag
         */
        MySynchronized(boolean method, boolean staticMethod, boolean block) {
            this.method = method;
            this.staticMethod = staticMethod;
            this.block = block;
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
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            if (this.method) {
                increase();
            } else if (this.staticMethod) {
                increaseStatic(loops);
            } else if (this.block) {
                increaseBlock();
            } else {
                increaseAtomic();
            }
        }
    }
}
