package ru.job4j;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Shift class.
 *
 * @author Denis
 * @since 01.04.2017
 */
class Shift {
    /**
     * Shift array.
     *
     * @param array original array
     * @param offset offset
     * @return shifted array or empty array (if array isn`t square)
     */
    int[][] shiftArray(int[][] array, int offset) {
        int[][] result = new int[array.length][array.length];
        if (checkArray(array)) {
            Deque<Integer> deque = arrayToDeque(array);
            shiftDeque(deque, offset);
            result = createShiftedArray(deque, array.length);
        }
        return result;
    }

    /**
     * Test array for square.
     *
     * @param array array for check
     * @return true if array is square
     */
    private boolean checkArray(int[][] array) {
        boolean result = true;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i].length != length) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Convert square array to deque.
     *
     * @param array array
     * @return deque
     */
    private Deque<Integer> arrayToDeque(int[][] array) {
        Deque<Integer> result = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                result.add(array[i][j]);
            }
        }
        return result;
    }

    /**
     * Shift deque.
     *
     * @param deque original deque
     * @param offset offset
     */
    private void shiftDeque(Deque<Integer> deque, int offset) {
        for (int i = 0; i < offset; i++) {
            deque.addFirst(deque.getLast());
            deque.removeLast();
        }
    }

    /**
     * Create shifted array from deque.
     *
     * @param deque shifted deque
     * @param length length of array
     * @return array
     */
    private int[][] createShiftedArray(Deque<Integer> deque, int length) {
        int[][] result = new int[length][length];
        Iterator<Integer> iterator = deque.iterator();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[i][j] = iterator.next();
            }
        }
        return result;
    }
}
