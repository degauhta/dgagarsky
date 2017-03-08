package ru.job4j;

/**
 * CycledList class.
 *
 * @param <E> type
 * @author Denis
 * @since 07.03.2017
 */
class CycledList<E> implements SimpleCycled<E> {
    /**
     * Main constructor.
     */
    CycledList() {
    }

    /**
     * Check for cycle.
     *
     * @param cycledNode cycled node
     * @return true if list has cycle
     */
    @Override
    public boolean hasCycle(Node<E> cycledNode) {
        Node current = cycledNode;
        do {
            current = current.next;
        } while (current != null && current != cycledNode);
        return current == cycledNode;
    }

    /**
     * Node class.
     *
     * @param <E> element
     */
    static class Node<E> {
        /**
         * Element.
         */
        private E element;

        /**
         * Link on next.
         */
        private Node<E> next;

        /**
         * Main constructor.
         *
         * @param element element
         */
        Node(E element) {
            this.element = element;
        }

        /**
         * Set next.
         *
         * @param next nex node.
         */
        void setNext(Node<E> next) {
            this.next = next;
        }
    }
}