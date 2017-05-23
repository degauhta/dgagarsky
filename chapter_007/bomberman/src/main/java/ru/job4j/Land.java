package ru.job4j;

/**
 * Land class.
 *
 * @author Denis
 * @since 12.05.2017
 */
class Land extends FixedGameObject {
    /**
     * Default constructor.
     *
     * @param position cell
     */
    Land(Cell position) {
        super(position, false);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "_ ";
    }



}
