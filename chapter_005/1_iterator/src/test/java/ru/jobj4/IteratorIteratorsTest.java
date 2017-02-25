package ru.jobj4;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * IteratorIteratorsTest class.
 *
 * @author Denis
 * @since 25.02.2017
 */
public class IteratorIteratorsTest {
    /**
     * Testing iterator of iterators last number.
     */
    @Test
    public void whenIteratorOfIteratorsThen() {
        Iterator<Integer> simpleIterator1 = new SimpleIterator<>(1, 2, 3);
        Iterator<Integer> simpleIterator2 = null;
        Iterator<Integer> simpleIterator3 = new SimpleIterator<>(100, 200, 300);
        Iterator<Iterator<Integer>> iterator = new SimpleIterator<>(simpleIterator1, simpleIterator2, simpleIterator3);
        Iterators iterators = new Iterators();
        Iterator it = iterators.convert(iterator);
        for (int i = 0; i < 5; i++) {
            it.next();
        }
        assertThat(it.next(), is(300));
    }

    /**
     * Iteration exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenOutOfIterationThenException() {
        Iterator<Integer> simpleIterator1 = new SimpleIterator<>(1, 2);
        Iterator<Integer> simpleIterator2 = new SimpleIterator<>(2, 3);
        Iterator<Iterator<Integer>> iterator = new SimpleIterator<>(simpleIterator1, simpleIterator2);
        Iterators iterators = new Iterators();
        Iterator it = iterators.convert(iterator);
        for (int i = 0; i < 5; i++) {
            it.next();
        }
    }
}