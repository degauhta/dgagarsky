package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * AlgoArrayDistanceTest class.
 *
 * @author Denis
 * @since 05.02.2017
 */
public class AlgoArrayDistanceTest {
    /**
     * Test array with same numbers.
     */
    @Test
    public void whenArrayContainSameNumberThenReturnDistance() {
        AlgoArrayDistance distance = new AlgoArrayDistance();
        int[] array = {1, 2, 3, 2, 1, 3, 1, 2, 2, 7};
        assertThat(distance.findMaxDistanceBetweenSameNumber(array), is(7));
    }

    /**
     * Test array without same numbers.
     */
    @Test
    public void whenArrayNotContainSameNumberThenReturnMinusOne() {
        AlgoArrayDistance distance = new AlgoArrayDistance();
        int[] array = {1, 2, 3, 4};
        assertThat(distance.findMaxDistanceBetweenSameNumber(array), is(-1));
    }
    /**
     * Test not suitable.
     */
    @Test
    public void whenNotSuitableArrayThenReturnMinusOne() {
        AlgoArrayDistance distance = new AlgoArrayDistance();
        int[] array = new int[1];
        assertThat(distance.findMaxDistanceBetweenSameNumber(array), is(-1));
    }
}