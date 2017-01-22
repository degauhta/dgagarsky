package ru.job4j;

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
     * Read ip & port from app.properties.
     * Create socket & trying connect to the server.
     */
    void connectToServer();

    /**
     * Save file from server.
     */
    void saveFile();

    /**
     * Load file to server.
     */
    void loadFile();

    /**
     * Print current catalogs.
     */
    void printCatalogs();
}
