package ru.job4j;

/**
 * ImpossibleMoveException class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message of error.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
