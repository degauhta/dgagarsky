package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * LinkedQueueTest class.
 *
 * @author Denis
 * @since 03.03.2017
 */
public class LinkedQueueTest {
    /**
     * Test queue. FIFO.
     */
    @Test
    public void whenPollThenReturnLastPushedElement() {
        SimpleStack<Integer> stack = new LinkedQueue<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(1));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(3));
    }

    /**
     * Test queue exception.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void whenPollFromEmptyThenException() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.poll();
    }
}