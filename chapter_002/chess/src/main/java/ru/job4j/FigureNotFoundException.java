package ru.job4j;

/**
 * FigureNotFoundException class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message of error.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
