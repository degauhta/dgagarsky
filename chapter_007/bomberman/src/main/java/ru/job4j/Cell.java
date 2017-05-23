package ru.job4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cell class.
 *
 * @author Denis
 * @since 12.05.2017
 */
class Cell {
    /**
     * Game object.
     */
    private Object gameObject;

    /**
     * Cells lock.
     */
    private Lock lock = new ReentrantLock();

    /**
     * Row number.
     */
    private int row;

    /**
     * Column number.
     */
    private int column;

    /**
     * Default constructor.
     *
     * @param row    row number
     * @param column column number
     */
    Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Set game object.
     *
     * @param newGameObject new game object
     * @return true if set successfully
     */
    boolean setGameObject(Object newGameObject) {
        boolean result = false;
        Cell oldPosition = ((Blockable) newGameObject).getPosition();
        if (oldPosition != this && ((Blockable) newGameObject).isBlocking()) {
            try {
                result = lock.tryLock(10L, TimeUnit.MILLISECONDS);
                if (result) {
                    this.gameObject = newGameObject;
                    oldPosition.getLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.gameObject = newGameObject;
        }
        return result;
    }

    /**
     * Get lock of cell.
     *
     * @return lock
     */
    Lock getLock() {
        return lock;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return gameObject.toString();
    }

    /**
     * Get row number of cell.
     *
     * @return row
     */
    int getRow() {
        return row;
    }

    /**
     * Get column number of cell.
     *
     * @return column
     */
    int getColumn() {
        return column;
    }
}
