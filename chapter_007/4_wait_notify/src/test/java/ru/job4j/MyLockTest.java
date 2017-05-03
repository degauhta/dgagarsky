package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * MyLockTest class.
 *
 * @author Denis
 * @since 03.05.2017
 */
public class MyLockTest {
    /**
     * Thread 1.
     */
    private Thread th1;

    /**
     * Thread 2.
     */
    private Thread th2;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        MyLock myLock = new MyLock();
        th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myLock.lock();
                System.out.println("th1 - waiting 2 second");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myLock.unlock();
            }
        });
        th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myLock.lock();
                System.out.println("th2");
                myLock.unlock();
            }
        });
    }

    /**
     * Test thread1 first.
     *
     * @throws InterruptedException error
     */
    @Test
    public void whenStartFirstThenSecondThreadThenMessageInSameOrder() throws InterruptedException {
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
        String expected = Joiner.on(System.lineSeparator()).join("th1 - waiting 2 second",
                "th2", "");
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(actual.toString(), is(expected));
    }

    /**
     * Test thread1 first.
     *
     * @throws InterruptedException error
     */
    @Test
    public void whenStartSecondThenFirstThreadThenMessageInSameOrder() throws InterruptedException {
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
        String expected = Joiner.on(System.lineSeparator()).join("th2",
                "th1 - waiting 2 second", "");
        th2.start();
        th1.start();
        th1.join();
        th2.join();
        assertThat(actual.toString(), is(expected));
    }
}