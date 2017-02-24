package ru.jobj4;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * IteratorEvenTest class.
 *
 * @author Denis
 * @since 23.02.2017
 */
public class IteratorEvenTest {
    /**
     * Iterator1 have next even number.
     */
    @Test
    public void whenIteratorHaveEvenNumberThanReturnThisNumber() {
        IteratorEven iteratorEven = new IteratorEven(new int[]{1, 3, 4, 5, 6});
        iteratorEven.next();
        int actual = (int) iteratorEven.next();
        assertThat(actual, is(6));
    }

    /**
     * Iterator1 haven`t next even number.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenIteratorHaveNotEvenThanReturnException() {
        IteratorEven iteratorEven = new IteratorEven(new int[]{1, 3, 4, 5, 7});
        iteratorEven.next();
        iteratorEven.next();
    }

    /**
     * Iterator1 have next even number.
     */
    @Test
    public void whenIteratorHaveEvenThenReturnTrue() {
        IteratorEven iteratorEven = new IteratorEven(new int[]{1, 3, 5, 2});
        boolean actual = iteratorEven.hasNext();
        assertThat(actual, is(true));
    }

    /**
     * Iterator1 haven`t next even number.
     */
    @Test
    public void whenIteratorHaveNotEvenThenReturnFalse() {
        IteratorEven iteratorEven = new IteratorEven(new int[]{1, 3, 5});
        boolean actual = iteratorEven.hasNext();
        assertThat(actual, is(false));
    }
}