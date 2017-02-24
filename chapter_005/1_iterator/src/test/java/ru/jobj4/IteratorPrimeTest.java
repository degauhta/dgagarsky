package ru.jobj4;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * IteratorPrimeTest class.
 *
 * @author Denis
 * @since 24.02.2017
 */
public class IteratorPrimeTest {
    /**
     * Prime number 2.
     */
    @Test
    public void whenIteratorHaveFirstPrimeNumber2ThanReturn2() {
        IteratorPrime prime = new IteratorPrime(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        int actual = (int) prime.next();
        assertThat(actual, is(2));
    }

    /**
     * Prime number 3.
     */
    @Test
    public void whenIteratorHaveSecondPrimeNumber3ThanReturn3() {
        IteratorPrime prime = new IteratorPrime(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        prime.next();
        int actual = (int) prime.next();
        assertThat(actual, is(3));
    }

    /**
     * Prime number 5.
     */
    @Test
    public void whenIteratorHaveThirdPrimeNumber5ThanReturn5() {
        IteratorPrime prime = new IteratorPrime(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        prime.next();
        prime.next();
        int actual = (int) prime.next();
        assertThat(actual, is(5));
    }

    /**
     * Prime number 7.
     */
    @Test
    public void whenIteratorHaveFourthPrimeNumber7ThanReturn7() {
        IteratorPrime prime = new IteratorPrime(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        prime.next();
        prime.next();
        prime.next();
        int actual = (int) prime.next();
        assertThat(actual, is(7));
    }

    /**
     * Prime number 11.
     */
    @Test
    public void whenIteratorHaveFifthPrimeNumber11ThanReturn11() {
        IteratorPrime prime = new IteratorPrime(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        prime.next();
        prime.next();
        prime.next();
        prime.next();
        int actual = (int) prime.next();
        assertThat(actual, is(11));
    }

    /**
     * Iterator1 have next even number.
     */
    @Test
    public void whenIteratorHavePrimeThenReturnTrue() {
        IteratorPrime prime = new IteratorPrime(new int[]{1, 6, 5, 2});
        boolean actual = prime.hasNext();
        assertThat(actual, is(true));
    }

    /**
     * Iterator1 haven`t next even number.
     */
    @Test
    public void whenIteratorHaveNotPrimeThenReturnFalse() {
        IteratorPrime prime = new IteratorPrime(new int[]{1, 6, 4});
        boolean actual = prime.hasNext();
        assertThat(actual, is(false));
    }
}