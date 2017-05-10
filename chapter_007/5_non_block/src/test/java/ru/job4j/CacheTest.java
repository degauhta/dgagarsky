package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CacheTest class.
 *
 * @author Denis
 * @since 06.05.2017
 */
public class CacheTest {
    /**
     * Test add.
     *
     * @throws InterruptedException error
     */
    @Test
    public void whenAdd2EntryThenSizeIs2() throws InterruptedException {
        Cache cache = new Cache();
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.add(0, new Model("zero"));
            }
        }, "th-1");
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.add(1, new Model("first"));
            }
        }, "th-2");
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(cache.size(), is(2));
    }

    /**
     * Test delete.
     *
     * @throws InterruptedException error
     */
    @Test
    public void whenAdd3EntryAndDelete2EntryThenSizeIs1() throws InterruptedException {
        Cache cache = new Cache();
        cache.add(0, new Model("zero"));
        cache.add(1, new Model("first"));
        cache.add(2, new Model("second"));
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.delete(2);
            }
        }, "th-1");
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.delete(1);
            }
        }, "th-2");
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(cache.size(), is(1));
    }

    /**
     * Test 3 threads update.
     *
     * @throws InterruptedException error
     */
    @Test
    public void when3ThreadUpdateSameKeyThenVersionIs3() throws InterruptedException {
        Cache cache = new Cache();
        cache.add(0, new Model("zero"));
        cache.add(1, new Model("first"));
        cache.add(2, new Model("second"));
        cache.add(3, new Model("third"));
        cache.add(4, new Model("fourth"));
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.update(0, "new zero");
            }
        }, "th-1");
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.update(0, "new zero");
            }
        }, "th-2");
        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.update(0, "new zero");
            }
        }, "th-3");
        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
        assertThat(cache.get(0).getName(), is("new zero"));
        assertThat(cache.get(0).getVersion(), is(3));
    }


    /**
     * Stub.
     */
    @Test(expected = OptimisticException.class)
    public void stub() {
        throw new OptimisticException();
    }
}
