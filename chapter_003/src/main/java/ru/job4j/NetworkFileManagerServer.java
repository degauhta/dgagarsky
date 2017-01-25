package ru.job4j;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

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
     * Execute received command.
     *
     * @param socket client socket.
     * @param prop properties.
     * @throws IOException .
     */
    void executeCommand(Socket socket, Properties prop) throws IOException;
}
