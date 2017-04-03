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
    public void when() {
        Space space = new Space();
        try {
            space.count("Object-oriented programming (OOP) is at the core of Java");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(space.getWords(), is(9));
        assertThat(space.getSpaces(), is(8));
    }
}