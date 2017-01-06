package ru.job4j.start;

/**
 * MenuOutException class.
 *
 * @author Denis
 * @since 02.01.2017
 */
public class MenuOutException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message of error.
     */
    public MenuOutException(String message) {
        super(message);
    }
}
