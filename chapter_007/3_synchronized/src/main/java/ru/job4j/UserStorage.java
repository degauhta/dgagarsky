package ru.job4j;

import java.util.ArrayList;
import java.util.List;

/**
 * UserStorage class.
 *
 * @param <E> type
 * @author Denis
 * @since 12.04.2017
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
     * @return true if added successfully
     */
    synchronized boolean add(E e) {
        boolean result = false;
        if (!this.storage.contains(e)) {
            result = storage.add(e);
        }
        return result;
    }

    /**
     * Edit first occurrence.
     *
     * @param old old element
     * @param e   new element
     * @return true if edited successfully
     */
    synchronized boolean edit(E old, E e) {
        boolean result = false;
        int index = this.storage.indexOf(old);
        if (index >= 0) {
            this.storage.set(index, e);
            result = true;
        }
        return result;
    }

    /**
     * Deleted element.
     *
     * @param e element
     * @return true if element deleted
     */
    synchronized boolean delete(E e) {
        return storage.remove(e);
    }

    /**
     * Print storage.
     */
    synchronized void print() {
        for (E e : storage) {
            System.out.println(String.format("%s - %s", e.getName(), e.getAmount()));
        }
    }

    /**
     * Get size of storage.
     *
     * @return size
     */
    synchronized int getSize() {
        return this.storage.size();
    }

    /**
     * Transfer money.
     *
     * @param from element
     * @param to   element
     * @param sum  transfer sum
     */
    synchronized void transfer(E from, E to, int sum) {
        if (from != null && to != null && sum > 0 && from.getAmount() >= sum) {
            from.setAmount(from.getAmount() - sum);
            to.setAmount(to.getAmount() + sum);
        } else {
            System.out.println("Bad transfer");
        }
    }
}
