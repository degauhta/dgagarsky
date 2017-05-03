package ru.job4j;

import java.util.Queue;

/**
 * Consumer class.
 *
 * @author Denis
 * @since 26.04.2017
 */
class Consumer implements Runnable {
    /**
     * Shared queue.
     */
    private final Queue<Integer> sharedQueue;

    /**
     * Size of queue.
     */
    private final int size;

    /**
     * Default constructor.
     *
     * @param sharedQueue sharedQueue queue
     * @param size        size queue
     */
    Consumer(Queue<Integer> sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.size = size;
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
        synchronized (this.sharedQueue) {
            while (this.sharedQueue.isEmpty()) {
                try {
                    this.sharedQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < this.size; i++) {
                System.out.println(String.format("Consumer %s", this.sharedQueue.poll()));
            }
        }
    }
}
