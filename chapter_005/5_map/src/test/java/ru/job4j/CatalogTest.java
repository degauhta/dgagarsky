package ru.job4j;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CatalogTest class.
 *
 * @author Denis
 * @since 08.03.2017
 */
public class CatalogTest {
    /**
     * Size of empty.
     */
    @Test
    public void whenGetSizeFromEpryThenReturn0() {
        Map<Integer, String> goods = new Catalog<>();
        assertThat(goods.getSize(), is(0));
    }

    /**
     * Add.
     */
    @Test
    public void whenInsertElementThenReturnTrueAndSizeIs1() {
        Map<Integer, String> goods = new Catalog<>();
        boolean actual = goods.insert(1, "Table");
        assertThat(actual, is(true));
        assertThat(goods.getSize(), is(1));
    }

    /**
     * Add 4 elements. Get size.
     */
    @Test
    public void whenInsert4ElementThenReturnSizeIs4() {
        Catalog<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        goods.insert(2, "Chair");
        goods.insert(3, "Bed");
        goods.insert(5, "Divan");
        assertThat(goods.getSize(), is(4));
    }

    /**
     * Get element that is not in collection.
     */
    @Test
    public void whenGetElementThatIsNotInCollectionThenReturnNull() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        assertThat(goods.get(5), is(nullValue()));
    }

    /**
     * Get element.
     */
    @Test
    public void whenGetElementThatIsInCollectionThenReturnElement() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(2, "Chair");
        goods.insert(3, "Bed");
        goods.insert(5, "Divan");
        assertThat(goods.get(5), is("Divan"));
    }

    /**
     * Collision. Get element.
     */
    @Test
    public void whenGetElementThatIsReplacedThenReturnElement() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(2, "Chair");
        goods.insert(2, "Bed");
        assertThat(goods.get(2), is("Bed"));
    }


    /**
     * Delete element that is not in collection.
     */
    @Test
    public void whenDeleteElementThatIsNotInCollectionThenReturnFalse() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        assertThat(goods.delete(2), is(false));
    }

    /**
     * Delete element that is in collection. In table.
     */
    @Test
    public void whenDeleteElementThatIsInTableThenReturnTrue() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        goods.insert(2, "Chair");
        assertThat(goods.delete(1), is(true));
        assertThat(goods.getSize(), is(1));
    }

    /**
     * Delete element that is in collection. Linked.
     */
    @Test
    public void whenDeleteElementThatIsLinkedThenReturnTrue() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        goods.insert(2, "Chair");
        goods.insert(3, "Bed");
        goods.insert(5, "Divan");
        assertThat(goods.delete(3), is(true));
        assertThat(goods.getSize(), is(3));
    }

    /**
     * Iterator hasNext.
     */
    @Test
    public void whenIteratorHasNextThenReturnTrue() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        goods.insert(2, "Chair");
        Iterator iterator = goods.iterator();
        assertThat(iterator.hasNext(), is(true));
    }

    /**
     * Iterator next.
     */
    @Test
    public void whenIteratorNextThenReturnNextElement() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        goods.insert(2, "Chair");
        goods.insert(3, "Bed");
        Iterator iterator = goods.iterator();
        assertThat(iterator.next(), is("Chair"));
    }

    /**
     * Iterator exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorOutOfRangeThenException() {
        Map<Integer, String> goods = new Catalog<>();
        goods.insert(1, "Table");
        Iterator iterator = goods.iterator();
        iterator.next();
        iterator.next();
    }
}