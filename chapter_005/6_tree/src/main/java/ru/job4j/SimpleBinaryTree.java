package ru.job4j;

import ru.job4j.BinaryTree.Leaf;

/**
 * SimpleBinaryTree class.
 *
 * @param <E> type
 * @author Denis
 * @since 12.03.2017
 */
public interface SimpleBinaryTree<E> {
    /**
     * Add new leaf.
     *
     * @param e value of new leaf
     */
    void put(E e);

    /**
     * Binary search.
     *
     * @param e searching value
     * @return leaf or null if value not found
     */
    Leaf<E> binarySearch(E e);

    /**
     * Get size.
     *
     * @return size
     */
    int getSize();
}
