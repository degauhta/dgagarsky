package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ArrayHashSetTest class.
 *
 * @author Denis
 * @since 05.03.2017
 */
public class ArrayHashSetTest {
    /**
     * Size empty set.
     */
    @Test
    public void whenGetSizeFromEmptyThenReturn0() {
        SimpleSet set = new ArrayHashSet();
        assertThat(set.size(), is(0));
    }

    /**
     * Size set with 1 element.
     */
    @Test
    public void whenGetSizeFromSetWith1ElementThenReturn0() {
        SimpleSet<String> set = new ArrayHashSet<>();
        set.add("one");
        assertThat(set.size(), is(1));
    }

    /**
     * Size when added duplicate.
     */
    @Test
    public void whenAdd2SameElementAndGetSizeThenReturn1() {
        SimpleSet<String> set = new ArrayHashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        set.add("two");
        assertThat(set.size(), is(4));
    }

    /**
     * Test removing element that isn`t in set.
     */
    @Test
    public void whenRemoveElementThatIsNotInSetThenReturnFalse() {
        SimpleSet<String> set = new ArrayHashSet<>();
        set.add("one");
        assertThat(set.remove("test"), is(false));
    }

    /**
     * Test removing element that is in set.
     */
    @Test
    public void whenRemoveElementThatIsInSetThenReturnTrue() {
        SimpleSet<String> set = new ArrayHashSet<>();
        set.add("one");
        assertThat(set.remove("one"), is(true));
        assertThat(set.size(), is(0));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorHasNextThenReturnTrue() {
        SimpleSet<String> set = new ArrayHashSet<>();
        set.add("one");
        Iterator iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorNextThenReturnElement() {
        SimpleSet<String> set = new ArrayHashSet<>();
        set.add("one");
        Iterator iterator = set.iterator();
        assertThat(iterator.next(), is("one"));
    }
}