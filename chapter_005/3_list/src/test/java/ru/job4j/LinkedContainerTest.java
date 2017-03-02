package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * LinkedContainerTest class.
 *
 * @author Denis
 * @since 02.03.2017
 */
public class LinkedContainerTest {
    /**
     * Test empty container size.
     */
    @Test
    public void whetGetSizeFromEmptyThenReturn0() {
        LinkedContainer<String> container = new LinkedContainer<>();
        int actual = container.size();
        assertThat(actual, is(0));
    }

    /**
     * Test container with one element size.
     */
    @Test
    public void whenAddOneElementThehSizeIsOne() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("one");
        int actual = container.size();
        assertThat(actual, is(1));
    }

    /**
     * Test get element. Negative index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElementIndexIsNegativeThenException() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.get(-1);
    }

    /**
     * Test get element. Index bigger than size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetElementIndexIsBiggerThenSizeException() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.get(1);
    }

    /**
     * Test get first element.
     */
    @Test
    public void whenGetFirstElementThenReturnFirstElement() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("one");
        String actual = container.get(0);
        assertThat(actual, is("one"));
    }

    /**
     * Test get middle element.
     */
    @Test
    public void whenGetMiddleElementThenReturnMiddleElement() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("one");
        container.add("two");
        container.add("three");
        container.add("four");
        container.add("five");
        String actual = container.get(2);
        assertThat(actual, is("three"));
    }

    /**
     * Test get last element.
     */
    @Test
    public void whenGetLastElementThenReturnLastElement() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("one");
        container.add("two");
        container.add("three");
        String actual = container.get(2);
        assertThat(actual, is("three"));
    }

    /**
     * Test remove first element.
     */
    @Test
    public void whenRemoveFirstElementThenReturnFirstElement() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("one");
        container.add("two");
        container.add("three");
        String actual = container.remove(0);
        assertThat(actual, is("one"));
        assertThat(container.size(), is(2));
    }

    /**
     * Test remove middle element.
     */
    @Test
    public void whenRemoveMiddleElementThenReturnMiddleElement() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        int actual = container.remove(1);
        assertThat(actual, is(2));
        assertThat(container.size(), is(2));
    }

    /**
     * Test remove last element.
     */
    @Test
    public void whenRemoveLastElementThenReturnLastElement() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("A");
        container.add("B");
        container.add("C");
        String actual = container.remove(2);
        assertThat(actual, is("C"));
        assertThat(container.size(), is(2));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorThen() {
        LinkedContainer<String> container = new LinkedContainer<>();
        container.add("A");
        container.add("B");
        container.add("C");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : container) {
            stringBuilder.append(s);
        }
        assertThat(stringBuilder.toString(), is("ABC"));
    }
}