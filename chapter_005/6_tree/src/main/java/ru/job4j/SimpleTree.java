package ru.job4j;

import ru.job4j.Tree.Leaf;

import java.util.List;

/**
 * SimpleTree class.
 *
 * @param <E> key type
 * @author Denis
 * @since 10.03.2017
 */
public interface SimpleTree<E> {
    /**
     * Add leaf.
     *
     * @param leaf parent leaf
     * @param e value of new leaf
     * @return new leaf
     */
    Leaf<E> addChild(Leaf<E> leaf, E e);

    /**
     * Get list of children.
     *
     * @return list of children
     */
    List<E> getChildren();

    /**
     * Get size.
     *
     * @return size
     */
    int getSize();

    /**
     * Find first occurrence.
     *
     * @param current current leaf
     * @param e searching value
     * @return leaf or null if value not found.
     */
    Leaf<E> findLeaf(Leaf<E> current, E e);

    /**
     * Check fo balance.
     *
     * @param current root
     * @return true if tree is balanced
     */
    boolean checkForBalance(Leaf<E> current);
}