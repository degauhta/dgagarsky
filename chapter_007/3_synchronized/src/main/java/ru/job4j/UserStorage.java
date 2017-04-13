package ru.job4j;

import java.util.ArrayList;
import java.util.List;

/**
 * UserStorage class.
 *
 * @author Denis
 * @since 12.04.2017
 * @param <E> type
 */
class UserStorage<E extends User> {
    /**
     * User storage.
     */
    private List<E> storage;

    /**
     * Default constructor.
     */
    UserStorage() {
        this.storage = new ArrayList<E>();
    }

    /**
     * Add element.
     *
     * @param e element
     */
    void add(E e) {
        storage.add(e);
    }

    /**
     * Deleted element.
     *
     * @param e element
     * @return true if element deleted
     */
    boolean delete(E e) {
        return storage.remove(e);
    }

    /**
     * Print storage.
     */
    void print() {
        for (E e : storage) {
            System.out.println(String.format("%s - %s", e.getName(), e.getAmount()));
        }
    }

    /**
     * Get size of storage.
     *
     * @return size
     */
    int getSize() {
        return this.storage.size();
    }

    /**
     * Transfer money.
     *
     * @param from element
     * @param to   element
     * @param sum  transfer sum
     */
    void transfer(E from, E to, int sum) {
        if (sum > 0 && from.getAmount() >= sum) {
            Thread th = new Thread(new Transaction(from, to, sum));
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not enough money");
        }
    }

    /**
     * Transaction money class.
     */
    private class Transaction implements Runnable {
        /**
         * From element.
         */
        private final E from;

        /**
         * To element.
         */
        private final E to;

        /**
         * Sum.
         */
        private int sum;

        /**
         * Default constructor.
         *
         * @param from element
         * @param to   element
         * @param sum  transfer sum
         */
        Transaction(E from, E to, int sum) {
            this.from = from;
            this.to = to;
            this.sum = sum;
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
            startTransfer();
        }

        /**
         * Transfer money.
         */
        private void startTransfer() {
            if (from.hashCode() > to.hashCode()) {
                synchronized (to) {
                    synchronized (from) {
                        from.setAmount(from.getAmount() - sum);
                        to.setAmount(to.getAmount() + sum);
                    }
                }
            } else {
                synchronized (from) {
                    synchronized (to) {
                        from.setAmount(from.getAmount() - sum);
                        to.setAmount(to.getAmount() + sum);
                    }
                }
            }
        }
    }
}
