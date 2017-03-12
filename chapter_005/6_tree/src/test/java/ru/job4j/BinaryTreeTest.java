package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BinaryTreeTest class.
 *
 * @author Denis
 * @since 12.03.2017
 */
public class BinaryTreeTest {
    /**
     * Add 5 element.
     */
    @Test
    public void whenAdd5ElementThenSizeIs5() {
        SimpleBinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.put(5);
        binaryTree.put(1);
        binaryTree.put(2);
        binaryTree.put(3);
        binaryTree.put(6);
        assertThat(binaryTree.getSize(), is(5));
    }

    /**
     * Search value that in tree.
     */
    @Test
    public void whenSearchValueThatInTreeThenReturnLeafWithThatValue() {
        SimpleBinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.put(5);
        binaryTree.put(1);
        binaryTree.put(2);
        binaryTree.put(3);
        binaryTree.put(6);
        assertThat(binaryTree.binarySearch(3).getValue(), is(3));
    }

    /**
     * Search value that is not in tree.
     */
    @Test
    public void whenSearchValueThatIsNotInTreeThenReturnNull() {
        SimpleBinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.put(5);
        binaryTree.put(1);
        binaryTree.put(2);
        assertThat(binaryTree.binarySearch(35), is(nullValue()));
    }
}