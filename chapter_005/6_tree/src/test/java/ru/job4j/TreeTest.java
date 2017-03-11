package ru.job4j;

import org.junit.Test;
import ru.job4j.Tree.Leaf;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TreeTest class.
 *
 * @author Denis
 * @since 10.03.2017
 */
public class TreeTest {
    /**
     * Test size new tree.
     */
    @Test
    public void whenGetSizeFromNewTreeThenReturn0() {
        SimpleTree<String> tree = new Tree<>();
        assertThat(tree.getSize(), is(0));
    }

    /**
     * Test size. Tree with 3 elements.
     */
    @Test
    public void whenGetSizeFromTreeWith3LeafThenReturn3() {
        SimpleTree<String> tree = new Tree<>();
        Leaf<String> root = tree.addChild(null, "root");
        Leaf<String> oneRootLeft = tree.addChild(root, "oneRootLeft");
        Leaf<String> oneRootRight = tree.addChild(root, "oneRootRight");
        assertThat(tree.getSize(), is(3));
    }

    /**
     * Test get children.
     */
    @Test
    public void whenGetChildrenThenReturnListOfChildren() {
        SimpleTree<String> tree = new Tree<>();
        Leaf<String> root = tree.addChild(null, "root");
        Leaf<String> oneRootLeft = tree.addChild(root, "oneRootLeft");
        Leaf<String> oneRootRight = tree.addChild(root, "oneRootRight");
        Leaf<String> twoOneLeft = tree.addChild(oneRootLeft, "twoOneLeft");
        Leaf<String> twoOneRight = tree.addChild(oneRootLeft, "twoOneRight");
        List<String> expected = Arrays.asList("twoOneLeft", "twoOneRight", "oneRootLeft", "oneRootRight", "root");
        assertThat(tree.getChildren(), is(expected));
    }

    /**
     * Test find children.
     */
    @Test
    public void whenFindChildrenThatIsInTreeThenReturnLeaf() {
        SimpleTree<String> tree = new Tree<>();
        Leaf<String> root = tree.addChild(null, "root");
        Leaf<String> oneRootLeft = tree.addChild(root, "oneRootLeft");
        Leaf<String> oneRootRight = tree.addChild(root, "oneRootRight");
        Leaf<String> twoOneLeft = tree.addChild(oneRootLeft, "twoOneLeft");
        Leaf<String> twoOneRight = tree.addChild(oneRootLeft, "twoOneRight");
        assertThat(tree.findLeaf(root, "twoOneLeft"), is(twoOneLeft));
    }

    /**
     * Test find children.
     */
    @Test
    public void whenFindChildrenThatIsNotInTreeThenReturnNull() {
        SimpleTree<String> tree = new Tree<>();
        Leaf<String> root = tree.addChild(null, "root");
        Leaf<String> oneRootLeft = tree.addChild(root, "oneRootLeft");
        Leaf<String> twoOneLeft = tree.addChild(oneRootLeft, "twoOneLeft");
        assertThat(tree.findLeaf(root, "123"), is(nullValue()));
    }

    /**
     * Test balance of balanced tree.
     */
    @Test
    public void whenCheckBalanceOfBalancedTreeThenReturnTrue() {
        SimpleTree<String> tree = new Tree<>();
        Leaf<String> root = tree.addChild(null, "root");
        Leaf<String> oneRootLeft = tree.addChild(root, "oneRootLeft");
        Leaf<String> oneRootRight = tree.addChild(root, "oneRootRight");
        Leaf<String> twoOneLeft = tree.addChild(oneRootLeft, "twoOneLeft");
        Leaf<String> twoOneRight = tree.addChild(oneRootLeft, "twoOneRight");
        assertThat(tree.checkForBalance(root), is(true));
    }

    /**
     * Test balance of not balanced tree.
     */
    @Test
    public void whenCheckBalanceOfNotBalancedTreeThenReturnTrue() {
        SimpleTree<String> tree = new Tree<>();
        Leaf<String> root = tree.addChild(null, "root");
        Leaf<String> oneRootLeft = tree.addChild(root, "oneRootLeft");
        Leaf<String> oneRootRight = tree.addChild(root, "oneRootRight");
        Leaf<String> twoOneLeft = tree.addChild(oneRootLeft, "twoOneLeft");
        assertThat(tree.checkForBalance(root), is(false));
    }
}