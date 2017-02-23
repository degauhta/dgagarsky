package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * IslandTest class.
 *
 * @author Denis
 * @since 23.02.2017
 */
public class IslandTest {
    /**
     * Map is null.
     */
    @Test
    public void whenMapIsNullThenReturn0() {
        int[][] map = null;
        Island island = new Island();
        assertThat(island.findBiggerIsland(map), is(0));
    }

    /**
     * Map size is zero.
     */
    @Test
    public void whenMapSize0ThenReturn0() {
        int[][] map = new int[0][0];
        Island island = new Island();
        assertThat(island.findBiggerIsland(map), is(0));
    }

    /**
     * Map not square.
     */
    @Test
    public void whenMapNotSquareThenReturn0() {
        int[][] map = new int[1][2];
        Island island = new Island();
        assertThat(island.findBiggerIsland(map), is(0));
    }

    /**
     * One island.
     */
    @Test
    public void whenOneStrangeIslandSize13ThenReturn13() {
        int[][] map = new int[][]{
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 1, 1, 1}};
        Island island = new Island();
        assertThat(island.findBiggerIsland(map), is(13));
    }

    /**
     * Four island (13, 3, 4, 6).
     */
    @Test
    public void whenFourIslandThenReturnBigger() {
        int[][] map = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0}};
        Island island = new Island();
        assertThat(island.findBiggerIsland(map), is(13));
    }
}