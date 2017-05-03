package ru.job4j;

/**
 * MyLock class.
 *
 * @author Denis
 * @since 03.05.2017
 */
class MyLock {
    /**
     * Lock field.
     */
    private boolean lock;

    /**
     * If lock already set do nothing.
     * If not causes the current thread to wait.
     */
    synchronized void lock() {
        if (!this.lock) {
            this.lock = true;
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.lock = true;
        }
    }

    /**
     * Release this lock.
     */
    synchronized void unlock() {
        if (this.lock) {
            this.lock = false;
            notifyAll();
        }
    }
}
