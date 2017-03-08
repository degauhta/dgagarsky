package ru.job4j;

import ru.job4j.CycledList.Node;

/**
 * SimpleCycled class.
 *
 * @param <E> type
 * @author Denis
 * @since 07.03.2017
 */
public interface SimpleCycled<E> {
    /**
     * Check for cycle.
     *
     * @param cycledNode cycled node
     * @return true if list has cycle
     */
    boolean hasCycle(Node<E> cycledNode);
}
