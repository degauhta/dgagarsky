package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleArrayTest class.
 *
 * @author Denis
 * @since 25.02.2017
 */
public class SimpleArrayTest {
    /**
     *  Test add.
     */
    @Test
    public void whenSuccessfullyAddedElementThenReturnTrue() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        boolean actual = simpleArray.add("one");

        assertThat(actual, is(true));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddedElementInFullSimpleArrayThenReturnFalse() {
        SimpleArray<String> simpleArray = new SimpleArray<>(1);

        simpleArray.add("one");
        boolean actual = simpleArray.add("two");

        assertThat(actual, is(false));
    }

    /**
     *  Test delete.
     */
    @Test
    public void whenSuccessfullyDeleteElementThenReturnTrue() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        boolean actual = simpleArray.delete("one");

        assertThat(actual, is(true));
    }

    /**
     *  Test delete.
     */
    @Test
    public void whenDeleteElementThatNotInSimpleArrayThenReturnFalse() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        boolean actual = simpleArray.delete("two");

        assertThat(actual, is(false));
    }

    /**
     *  Test delete.
     */
    @Test
    public void whenDeleteNullElementInSimpleArrayThenReturnFalse() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        boolean actual = simpleArray.delete("321");

        assertThat(actual, is(false));
    }

    /**
     * Test get.
     */
    @Test
    public void whenGetElementFromSimpleArrayThenReturnElement() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        String actual = simpleArray.get(0);

        assertThat(actual, is("one"));
    }

    /**
     * Test get.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElementWithWrongPositionThenException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        String actual = simpleArray.get(11);
    }

    /**
     *  Test update.
     */
    @Test
    public void whenSuccessfullyUpdateElementThenReturnOldElement() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        String actual = simpleArray.update("123", 0);

        assertThat(actual, is("one"));
    }

    /**
     *  Test update.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenUpdateElementWithWrongPositionThenException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);

        simpleArray.add("one");
        simpleArray.update(null, 11);
    }
}