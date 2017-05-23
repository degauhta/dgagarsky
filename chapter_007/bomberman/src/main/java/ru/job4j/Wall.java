package ru.job4j;

/**
 * Wall class.
 *
 * @author Denis
 * @since 12.05.2017
 */
class Wall extends FixedGameObject {
    /**
     * Default constructor.
     *
     * @param position cell
     */
    Wall(Cell position) {
        super(position, false);
        position.getLock().lock();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "W ";
    }
}
