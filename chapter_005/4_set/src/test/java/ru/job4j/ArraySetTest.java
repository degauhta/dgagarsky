package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ArraySetTest class.
 *
 * @author Denis
 * @since 05.03.2017
 */
public class ArraySetTest {
    /**
     * Size empty set.
     */
    @Test
    public void whenGetSizeFromEmptyThenReturn0() {
        SimpleSet set = new ArraySet();
        assertThat(set.size(), is(0));
    }

    /**
     * Size set with 15 elements.
     */
    @Test
    public void whenGetSizeFromSetWith15ElementThenReturn15() {
        SimpleSet<Integer> set = new ArraySet<>();
        for (int i = 0; i < 15; i++) {
            set.add(i);
        }
        assertThat(set.size(), is(15));
    }

    /**
     * Size when added duplicate.
     */
    @Test
    public void whenAdd2SameElementAndGetSizeThenReturn1() {
        SimpleSet<String> set = new ArraySet<>();
        set.add("one");
        set.add("one");
        assertThat(set.size(), is(1));
    }

    /**
     * Test removing element that is not in set.
     */
    @Test
    public void whenRemoveElementThatIsNotInSetThenReturnFalse() {
        SimpleSet<String> set = new ArraySet<>();
        set.add("one");
        assertThat(set.remove("test"), is(false));
    }

    /**
     * Test removing element that is in set.
     */
    @Test
    public void whenRemoveElementThatIsInSetThenReturnTrue() {
        SimpleSet<String> set = new ArraySet<>();
        set.add("one");
        assertThat(set.remove("one"), is(true));
        assertThat(set.size(), is(0));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorHasNextThenReturnTrue() {
        SimpleSet<String> set = new ArraySet<>();
        set.add("one");
        Iterator iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorNextThenReturnElement() {
        SimpleSet<String> set = new ArraySet<>();
        set.add("one");
        Iterator iterator = set.iterator();
        assertThat(iterator.next(), is("one"));
    }
}