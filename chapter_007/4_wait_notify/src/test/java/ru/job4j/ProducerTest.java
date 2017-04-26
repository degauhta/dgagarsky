package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ProducerTest class.
 *
 * @author Denis
 * @since 26.04.2017
 */
public class ProducerTest {
    /**
     * Test producer - consumer queue.
     */
    @Test
    public void whenProducerFiledQueueThenConsumerPrintQueue() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        StringBuilder actual = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            actual.append(String.format("Consumer %s%s", i, System.lineSeparator()));
        }
        Thread th1 = new Thread(new Producer(queue, size));
        Thread th2 = new Thread(new Consumer(queue, size));
        th1.start();
        th2.start();
        try {
            th2.join();
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(actual.toString()));
    }
}