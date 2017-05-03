package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ThreadPoolTest class.
 *
 * @author Denis
 * @since 03.05.2017
 */
public class ThreadPoolTest {
    /**
     * Task 1.
     */
    private Runnable task1;

    /**
     * Task 2.
     */
    private Runnable task2;

    /**
     * Task 3.
     */
    private Runnable task3;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("task 1");
            }
        };
        task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("task 2");
            }
        };
        task3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("task 3");
            }
        };
    }

    /**
     * Test work.
     */
    @Test
    public void when3RunnableInThreadPoolThenPrint3Message() {
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
        String expected = Joiner.on(System.lineSeparator()).join("task 1",
                "task 2", "task 3", "");
        ThreadPool threadPool = new ThreadPool();
        threadPool.add(task1);
        threadPool.add(task2);
        threadPool.add(task3);
        threadPool.start();
        assertThat(actual.toString(), is(expected));
    }
}