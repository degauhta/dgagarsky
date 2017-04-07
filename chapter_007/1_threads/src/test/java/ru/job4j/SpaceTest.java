package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SpaceTest class.
 *
 * @author Denis
 * @since 02.04.2017
 */
public class SpaceTest {
    /**
     * Test counter words and spaces.
     */
    @Test
    public void whenInterruptedFalseThenReturnResult() {
        Space space = new Space();
        try {
            space.count("Object-oriented programming (OOP) is at the core of Java",
                    false, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(space.getWords(), is(9));
        assertThat(space.getSpaces(), is(8));
    }

    /**
     * Test interrupt.
     */
    @Test
    public void whenInterruptedThenReturn0() {
        Space space = new Space();
        try {
            space.count("Object-oriented programming (OOP) is at the core of Java",
                    true, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(space.getWords(), is(0));
        assertThat(space.getSpaces(), is(0));
    }

    /**
     * Test pause 1 second.
     */
    @Test ()
    public void whenPauseThenReturn0() {
        Space space = new Space();
        try {
            space.count("Java",
                    true, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(space.getWords(), is(0));
        assertThat(space.getSpaces(), is(0));
    }
}