package ru.job4j;

import java.io.IOException;

/**
 * NetworkFileManagerClient class.
 *
 * @author Denis
 * @since 22.01.2017
 */
public interface NetworkFileManagerClient {
    /**
     * Running client.
     * Read user command from input stream.
     */
    void run();

    /**
     * Save file from server.
     *
     * @throws IOException .
     */
    void saveFile() throws IOException;

    /**
     * Load file to server.
     *
     * @throws IOException .
     */
    void loadFile() throws IOException;

    /**
     * Print current catalogs.
     *
     * @throws IOException .
     */
    void printCatalogs() throws IOException;
}
