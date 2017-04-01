package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ShiftTest class.
 *
 * @author Denis
 * @since 01.04.2017
 */
public class ShiftTest {
    /**
     * Test square array.
     */
    @Test
    public void whenShiftArrayThenReturnShiftedArray() {
        Shift shift = new Shift();
        int[][] array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] expected = new int[][]{{14, 15, 16, 1}, {2, 3, 4, 5}, {6, 7, 8, 9}, {10, 11, 12, 13}};
        int[][] actual = shift.shiftArray(array, 3);
        assertThat(actual, is(expected));
    }

    /**
     * Test array.
     */
    @Test
    public void whenArrayNotSquareThenReturnEmptyArray() {
        Shift shift = new Shift();
        int[][] array = new int[][]{{1}, {2}};
        int[][] expected = new int[][]{{0, 0}, {0, 0}};
        int[][] actual = shift.shiftArray(array, 1);
        assertThat(actual, is(expected));
    }
}