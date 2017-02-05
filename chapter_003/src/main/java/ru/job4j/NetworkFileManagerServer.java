package ru.job4j;

import java.io.IOException;
import java.net.Socket;

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
     * @throws IOException .
     */
    void executeCommand(Socket socket) throws IOException;
}
