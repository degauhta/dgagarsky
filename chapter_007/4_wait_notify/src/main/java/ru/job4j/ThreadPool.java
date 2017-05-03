package ru.job4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ThreadPool class.
 *
 * @author Denis
 * @since 02.05.2017
 */
class ThreadPool {
    /**
     * Queue of runnable work.
     */
    private BlockingQueue<Runnable> works;

    /**
     * Array of thread.
     */
    private Thread[] pool;

    /**
     * Stop flag.
     */
    private boolean stop;

    /**
     * Default constructor.
     * Pool size of threads is equals number of CPU.
     */
    ThreadPool() {
        this.stop = false;
        this.works = new LinkedBlockingQueue<>();
        int processors = Runtime.getRuntime().availableProcessors();
        this.pool = new Thread[processors];
    }

    /**
     * Start thread pool.
     */
    void start() {
        for (int i = 0; i < this.pool.length; i++) {
            this.pool[i] = new MyThread();
            this.pool[i].start();
            try {
                this.pool[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Add runnable work.
     *
     * @param work work
     */
    synchronized void add(Runnable work) {
        this.works.add(work);
    }

    /**
     * Stop thread pool.
     */
    synchronized void stop() {
        this.stop = true;
        for (Thread th : pool) {
            th.interrupt();
        }
        System.out.println("stopped");
    }

    /**
     * My thread class.
     */
    private class MyThread extends Thread {
        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         */
        @Override
        public void run() {
            while (!stop && works.size() > 0) {
                if (works.size() > 0) {
                    Runnable work = works.poll();
                    work.run();
                }
            }
        }
    }
}
