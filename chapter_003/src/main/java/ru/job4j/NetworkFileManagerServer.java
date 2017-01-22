package ru.job4j;

/**
 * NetworkFileManagerServer class.
 *
 * @author Denis
 * @since 22.01.2017
 */
public interface NetworkFileManagerServer {
    /**
     * Running server with port from app.properties.
     */
    void run();

    /**
     * Trying to execute received command.
     */
    void executeCommand();
}
