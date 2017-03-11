package ru.job4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Tree class.
 *
 * @param <E> value type
 * @author Denis
 * @since 10.03.2017
 */
public class Tree<E> implements SimpleTree<E> {
    /**
     * Root.
     */
    private Leaf<E> root;

    /**
     * Size.
     */
    private int size;

    /**
     * Main constructor.
     */
    public Tree() {
    }

    /**
     * Add leaf.
     *
     * @param parent parent leaf
     * @throws IllegalArgumentException if child of leafs is not null
     */
    @Override
    public Leaf<E> addChild(Leaf<E> parent, E e) {
        Leaf<E> newLeaf = null;
        if (this.root == null) {
            this.root = new Leaf<E>(null, e);
            newLeaf = this.root;
        } else if (parent != null) {
            if (parent.left == null) {
                parent.left = new Leaf<E>(null, e);
                newLeaf = parent.left;
            } else if (parent.right == null) {
                parent.right = new Leaf<E>(null, e);
                newLeaf = parent.right;
            } else {
                throw new IllegalArgumentException();
            }
        }
        this.size++;
        return newLeaf;
    }

    /**
     * Get all children for leaf.
     *
     * @param current starting leaf
     * @return list of children's
     */
    private List<E> getLeafList(Leaf<E> current) {
        List<E> list = new LinkedList<>();
        if (current.left != null) {
            list.addAll(getLeafList(current.left));
        }
        if (current.right != null) {
            list.addAll(getLeafList(current.right));
        }
        list.add(current.value);
        return list;
    }

    /**
     * Get list of children.
     *
     * @return list of children
     */
    @Override
    public List<E> getChildren() {
        return getLeafList(this.root);
    }

    /**
     * Get size.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Find first occurrence.
     *
     * @param current current leaf
     * @param e       searching value
     * @return leaf or null if value not found.
     */
    @Override
    public Leaf<E> findLeaf(Leaf<E> current, E e) {
        Leaf<E> result = null;
        if (current.value.equals(e)) {
            return current;
        }
        if (current.left != null) {
            result = result == null ? findLeaf(current.left, e) : result;
        }
        if (current.right != null) {
            result = result == null ? findLeaf(current.right, e) : result;
        }
        return result;
    }

    /**
     * Check fo balance.
     *
     * @param current root
     * @return true if tree is balanced
     */
    @Override
    public boolean checkForBalance(Leaf<E> current) {
        boolean result = true;
        if (current.left != null ^ current.right != null) {
            return false;
        }
        if (current.left != null) {
            result = result ? checkForBalance(current.left) : result;
        }
        if (current.right != null) {
            result = result ? checkForBalance(current.right) : result;
        }
        return result;
    }

    /**
     * Entry aka leaf class.
     *
     * @param <E> value type
     */
    static final class Leaf<E> {
        /**
         * Parent.
         */

        private Leaf<E> parent;

        /**
         * Left.
         */

        private Leaf<E> left;
        /**
         * Right.
         */

        private Leaf<E> right;
        /**
         * Value.
         */

        private E value;

        /**
         * Main constructor.
         *
         * @param parent parent leaf
         * @param value value
         */
        Leaf(Leaf<E> parent, E value) {
            this.parent = parent;
            this.value = value;
        }
    }
}