package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CustomersCounterTest class.
 *
 * @author Denis
 * @since 19.03.2017
 */
public class CustomersCounterTest {
    /**
     * CustomersCounter.
     */
    private CustomersCounter counter;

    /**
     * Output stream.
     */
    private ByteArrayOutputStream outputStream;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        counter = new CustomersCounter();
    }

    /**
     * No overlap.
     */
    @Test
    public void whenNoOverlapThenPrintTimeFor1Customer() {
        String expected =
                Joiner.on(System.lineSeparator()).join("1 customers from 08:30:00 till 08:50:00",
                        "1 customers from 08:10:00 till 08:20:00", "");
        counter.addCustomer(8 * 3600 + 10 * 60, 8 * 3600 + 20 * 60);
        counter.addCustomer(8 * 3600 + 30 * 60, 8 * 3600 + 50 * 60);
        counter.printMostCustomersTime();
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * One overlap bigger then others.
     */
    @Test
    public void whenOneOverlapBiggerThenPrintThatOverlap() {
        String expected = Joiner.on(System.lineSeparator()).join(
                "4 customers from 08:45:00 till 08:50:00", "");
        counter.addCustomer(8 * 3600 + 10 * 60, 8 * 3600 + 40 * 60);
        counter.addCustomer(8 * 3600 + 20 * 60, 8 * 3600 + 50 * 60);
        counter.addCustomer(8 * 3600 + 20 * 60, 8 * 3600 + 50 * 60);
        counter.addCustomer(8 * 3600 + 45 * 60, 8 * 3600 + 50 * 60);
        counter.addCustomer(8 * 3600 + 45 * 60, 8 * 3600 + 50 * 60);
        counter.printMostCustomersTime();
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Two overlaps with same count of customers.
     */
    @Test
    public void whenTwoSameOverlapsThenTheseOverlaps() {
        String expected = Joiner.on(System.lineSeparator()).join(
                "2 customers from 08:25:00 till 08:40:00",
                "2 customers from 08:45:00 till 08:50:00", "");
        counter.addCustomer(8 * 3600 + 10 * 60, 8 * 3600 + 50 * 60);
        counter.addCustomer(8 * 3600 + 25 * 60, 8 * 3600 + 40 * 60);
        counter.addCustomer(8 * 3600 + 45 * 60, 8 * 3600 + 55 * 60);
        counter.printMostCustomersTime();
        assertThat(outputStream.toString(), is(expected));
    }
}