package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SyncCounterTest class.
 *
 * @author Denis
 * @since 11.04.2017
 */
public class SyncCounterTest {
    /**
     * Test synchronized method.
     */
    @Test
    public void whenSynchronizedMethodWithXLoopsThenReturn2X() {
        Counter counter = new Counter(50_000_000);
        counter.count(true, false, false);
        assertThat(counter.getShared(), is(100_000_000));
    }

    /**
     * Test synchronized static method.
     */
    @Test
    public void whenSynchronizedStaticMethodWithXLoopsThenReturn2X() {
        Counter counter = new Counter(50_000_000);
        counter.count(false, true, false);
        assertThat(Counter.getSharedStatic(), is(100_000_000));
    }

    /**
     * Test synchronized block.
     */
    @Test
    public void whenSynchronizedBlockWithXLoopsThenReturn2X() {
        Counter counter = new Counter(50_000_000);
        counter.count(false, false, true);
        assertThat(counter.getShared(), is(100_000_000));
    }

    /**
     * Test atomic.
     */
    @Test
    public void whenUseAtomicWithXLoopsThenReturn2X() {
        Counter counter = new Counter(50_000_000);
        counter.count(false, false, false);
        assertThat(counter.getSharerAtomicInteger(), is(100_000_000));
    }
}