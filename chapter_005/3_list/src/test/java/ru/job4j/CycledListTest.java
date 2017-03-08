package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.CycledList.Node;

/**
 * CycledListTest class.
 *
 * @author Denis
 * @since 07.03.2017
 */
public class CycledListTest {
    /**
     * Test node without loop.
     */
    @Test
    public void whenNoCycleThenReturnFalse() {
        SimpleCycled<Integer> container = new CycledList<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        assertThat(container.hasCycle(first), is(false));
    }

    /**
     * Test node with loop.
     */
    @Test
    public void whenCycleThenReturnTrue() {
        SimpleCycled<Integer> container = new CycledList<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
        assertThat(container.hasCycle(first), is(true));
    }
}