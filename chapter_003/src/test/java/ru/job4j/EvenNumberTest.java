package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * EvenNumberTest class.
 *
 * @author Denis
 * @since 14.01.2017
 */
public class EvenNumberTest {
    /**
     * Test even.
     */
    @Test
    public void whenEvenNumberThenTrue() {
        EvenNumber evenNumber = new EvenNumber();
        assertThat(evenNumber.isNumber(new ByteArrayInputStream(new byte[] {1, 2, 3, 5})), is(true));
    }

    /**
     * Test odd.
     */
    @Test
    public void whenOddNumberThenFalse() {
        EvenNumber evenNumber = new EvenNumber();
        assertThat(evenNumber.isNumber(new ByteArrayInputStream(new byte[] {1, 3, 5})), is(false));
    }
}