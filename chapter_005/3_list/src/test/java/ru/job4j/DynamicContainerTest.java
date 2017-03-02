package ru.job4j;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DynamicContainerTest class.
 *
 * @author Denis
 * @since 01.03.2017
 */
public class DynamicContainerTest {
    /**
     * Test size of empty container.
     */
    @Test
    public void whenGetSizeFromEmptyContainerReturn0() {
        DynamicContainer container = new DynamicContainer();
        int actual = container.size();
        assertThat(actual, is(0));
    }

    /**
     * Test size of container with 1 element.
     */
    @Test
    public void whenGetSizeFromContainerWith1ElementReturn1() {
        DynamicContainer<String> container = new DynamicContainer<>();
        container.add(null);
        int actual = container.size();
        assertThat(actual, is(1));
    }

    /**
     * Test grow of container.
     */
    @Test
    public void whenAddElementInFullContainerAndGetSizeThenReturnSizePlusOne() {
        DynamicContainer<String> container = new DynamicContainer<>(2);
        container.add("zero");
        container.add("one");
        container.add("two");
        int actual = container.size();
        assertThat(actual, is(3));
    }

    /**
     * Test get method.
     */
    @Test
    public void whenGetElementWithExistingIndexThenReturnThisElement() {
        DynamicContainer<String> container = new DynamicContainer<>();
        container.add("one");
        String actual = container.get(0);
        assertThat(actual, is("one"));
    }

    /**
     * Test get method with wrong index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElementWithIndexBiggerSizeThenException() {
        DynamicContainer<String> container = new DynamicContainer<>(1);
        container.add("one");
        container.get(123);
    }

    /**
     * Test get method with negative index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElementWithNegativeIndexThenException() {
        DynamicContainer<String> container = new DynamicContainer<>(1);
        container.add("one");
        container.get(-1);
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenGetIteratorThenReturnIterator() {
        DynamicContainer<String> container = new DynamicContainer<>(3);
        container.add("zero");
        container.add("one");
        container.add("two");
        Iterator iterator = container.iterator();
        assertThat(iterator.next(), is("zero"));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.next(), is("two"));
    }

    /**
     * Test iterator NoSuchElementException.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenIteratorIsOutThenException() {
        DynamicContainer<String> container = new DynamicContainer<>(1);
        container.add("zero");
        Iterator iterator = container.iterator();
        iterator.next();
        iterator.next();
    }

    /**
     * Test remove from beginning.
     */
    @Test
    public void whenRemoveElementFromBeginningThenReturnOldValue() {
        DynamicContainer<Integer> container = new DynamicContainer<>(2);
        container.add(0);
        container.add(1);
        assertThat(container.size(), is(2));
        assertThat(container.remove(0), is(0));
        assertThat(container.size(), is(1));
    }

    /**
     * Test remove from middle.
     */
    @Test
    public void whenRemoveElementFromMiddleThenReturnOldValue() {
        DynamicContainer<Integer> container = new DynamicContainer<>(3);
        container.add(0);
        container.add(1);
        container.add(2);
        assertThat(container.size(), is(3));
        assertThat(container.remove(1), is(1));
        assertThat(container.size(), is(2));
    }

    /**
     * Test remove from end.
     */
    @Test
    public void whenRemoveElementFromEndThenReturnOldValue() {
        DynamicContainer<String> container = new DynamicContainer<>(3);
        container.add("0");
        container.add("1");
        container.add("2");
        assertThat(container.size(), is(3));
        assertThat(container.remove(2), is("2"));
        assertThat(container.size(), is(2));
    }
}