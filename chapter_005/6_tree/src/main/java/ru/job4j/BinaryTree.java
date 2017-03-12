package ru.job4j;

/**
 * BinaryTree class.
 *
 * @param <E> type
 * @author Denis
 * @since 12.03.2017
 */
public class BinaryTree<E> implements SimpleBinaryTree<E> {
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
    BinaryTree() {
    }

    /**
     * Add new leaf.
     *
     * @param e value of new leaf
     */
    @Override
    public void put(E e) {
        if (this.root == null) {
            this.root = new Leaf<E>(null, e);
        } else {
            int cmp;
            Comparable<? super E> k = (Comparable<? super E>) e;
            Leaf<E> current = this.root;
            Leaf<E> parent = current;
            do {
                parent = current;
                cmp = k.compareTo(current.value);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    break;
                }
            } while (current != null);
            Leaf<E> newLeaf = new Leaf<E>(parent, e);
            if (cmp < 0) {
                parent.left = newLeaf;
            } else {
                parent.right = newLeaf;
            }
        }
        this.size++;
    }

    /**
     * Binary search.
     *
     * @param e searching value
     * @return leaf or null if value not found
     */
    @Override
    public Leaf<E> binarySearch(E e) {
        Leaf<E> result = null;
        Leaf<E> current = root;
        Comparable<? super E> k = (Comparable<? super E>) e;
        int cmp;
        do {
            cmp = k.compareTo(current.value);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                result = current;
                break;
            }
        } while (current != null);
        return result;
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
         * @param value  value
         */
        Leaf(Leaf<E> parent, E value) {
            this.parent = parent;
            this.value = value;
        }

        /**
         * Get value.
         *
         * @return value
         */
        public E getValue() {
            return value;
        }
    }
}
