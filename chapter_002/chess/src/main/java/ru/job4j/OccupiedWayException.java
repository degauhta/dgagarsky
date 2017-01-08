package ru.job4j;

/**
 * OccupiedWayException class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message of error.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
