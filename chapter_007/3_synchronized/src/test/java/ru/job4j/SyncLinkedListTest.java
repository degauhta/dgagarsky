package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SyncLinkedListTest class.
 *
 * @author Denis
 * @since 18.04.2017
 */
public class SyncLinkedListTest {
    /**
     * Test threads.
     *
     * @throws InterruptedException error
     */
    @Test
    public void when2ThreadAddXElementsThenSizeIs2X() throws InterruptedException {
        SyncLinkedList<Integer> list = new SyncLinkedList<>();
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    list.add(i);
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    list.add(i);
                }
            }
        });
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(list.size(), is(200_000));
    }

    /**
     * Test empty container size.
     */
    @Test
    public void whetGetSizeFromEmptyThenReturn0() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        int actual = container.size();
        assertThat(actual, is(0));
    }

    /**
     * Test container with one element size.
     */
    @Test
    public void whenAddOneElementThehSizeIsOne() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("one");
        int actual = container.size();
        assertThat(actual, is(1));
    }

    /**
     * Test get element. Negative index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementIndexIsNegativeThenException() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.get(-1);
    }

    /**
     * Test get element. Index bigger than size.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementIndexIsBiggerThenSizeException() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.get(1);
    }

    /**
     * Test get first element.
     */
    @Test
    public void whenGetFirstElementThenReturnFirstElement() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("one");
        String actual = container.get(0);
        assertThat(actual, is("one"));
    }

    /**
     * Test get middle element.
     */
    @Test
    public void whenGetMiddleElementThenReturnMiddleElement() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("one");
        container.add("two");
        container.add("three");
        container.add("four");
        container.add("five");
        String actual = container.get(2);
        assertThat(actual, is("three"));
    }

    /**
     * Test get last element.
     */
    @Test
    public void whenGetLastElementThenReturnLastElement() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("one");
        container.add("two");
        container.add("three");
        String actual = container.get(2);
        assertThat(actual, is("three"));
    }

    /**
     * Test remove first element.
     */
    @Test
    public void whenRemoveFirstElementThenReturnFirstElement() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("one");
        container.add("two");
        container.add("three");
        String actual = container.remove(0);
        assertThat(actual, is("one"));
        assertThat(container.size(), is(2));
    }

    /**
     * Test remove middle element.
     */
    @Test
    public void whenRemoveMiddleElementThenReturnMiddleElement() {
        SyncLinkedList<Integer> container = new SyncLinkedList<>();
        container.add(1);
        container.add(2);
        container.add(3);
        int actual = container.remove(1);
        assertThat(actual, is(2));
        assertThat(container.size(), is(2));
    }

    /**
     * Test remove last element.
     */
    @Test
    public void whenRemoveLastElementThenReturnLastElement() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("A");
        container.add("B");
        container.add("C");
        String actual = container.remove(2);
        assertThat(actual, is("C"));
        assertThat(container.size(), is(2));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorThen() {
        SyncLinkedList<String> container = new SyncLinkedList<>();
        container.add("A");
        container.add("B");
        container.add("C");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : container) {
            stringBuilder.append(s);
        }
        assertThat(stringBuilder.toString(), is("ABC"));
    }
}