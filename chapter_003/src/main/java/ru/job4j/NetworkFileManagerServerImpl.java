package ru.job4j;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * NetworkFileManagerServerImpl class.
 *
 * @author Denis
 * @since 23.01.2017
 */
public class NetworkFileManagerServerImpl implements NetworkFileManagerServer {
    /**
     * Properties.
     */
    private Properties prop;

    /**
     * Input socket stream.
     */
    private DataInputStream in;

    /**
     * Output socket stream.
     */
    private DataOutputStream out;

    /**
     * Running server with port from app.properties.
     */
    @Override
    public void run() {
        Properties prop = loadAppProperties();
        if (prop.getProperty("port") == null) {
            System.out.println("app.properties not loaded");
            return;
        }

        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
            while (true) {
                Socket socket = serverSocket.accept();
                executeCommand(socket, prop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute received command.
     *
     * @param socket client socket.
     * @param prop   properties.
     * @throws IOException .
     */
    @Override
    public void executeCommand(Socket socket, Properties prop) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        String line = "";
        out.writeUTF(prop.getProperty("currentDirectory"));
        out.flush();

        while (!"quit".equals(line)) {
            try {
                do {
                    line = in.readUTF();
                    System.out.println("client: " + line);
                } while (line.length() == 0);
            } catch (EOFException e) {
                break;
            }

            if (line.length() > 5 && "save ".equals(line.substring(0, 5))) {
                saveToClient(line);
            }

            if (line.length() > 3 && "cd ".equals(line.substring(0, 3))) {
                changeDirectory(line);
            }

            if ("dir".equals(line)) {
                sendDirToClient();
            }

            if ("load".equals(line)) {
                loadClientFile();
            }

            line = "";
            /*current*/
            out.writeUTF(prop.getProperty("currentDirectory"));
            out.flush();
        }
    }

    /**
     * Loading client file to current server folder.
     * @throws IOException .
     */
    private void loadClientFile() throws IOException {
        FileOutputStream fileOutStr;
        BufferedOutputStream buffOutStr;
        long fileSize = in.readLong();
        byte[] buffer = new byte[(int) fileSize];

        fileOutStr = new FileOutputStream(prop.getProperty("currentDirectory") + "\\srv.txt");
        buffOutStr = new BufferedOutputStream(fileOutStr);

        in.read(buffer, 0, buffer.length);
        buffOutStr.write(buffer);
        buffOutStr.flush();
        System.out.println(fileSize);
        System.out.println(prop.getProperty("Path") + "\\srv.txt");
    }

    /**
     * Sending list of files and subfolders of current folder to client.
     * @throws IOException .
     */
    private void sendDirToClient() throws IOException {
        System.out.println("sending dir");

        String dir = prop.getProperty("currentDirectory");
        File myFile = new File(dir);
        File[] files = myFile.listFiles();
        System.out.println("all dir");
        for (File file : files) {
            if (file.isDirectory()) {
                out.writeUTF(file.toString());
                out.flush();
            }
        }
        System.out.println("all files");
        for (File file : files) {
            if (file.isFile()) {
                out.writeUTF(file.toString());
                out.flush();
            }
        }
        out.writeUTF("endOfDir");
        out.flush();
    }

    /**
     * Change server folder.
     * @param line new folder.
     */
    private void changeDirectory(String line) {
        File dir;
        if (line.endsWith("..")) {
            dir = new File(prop.getProperty("currentDirectory"));
            prop.setProperty("currentDirectory", dir.getParent());
        } else {
            dir = new File(prop.getProperty("currentDirectory") + line.substring(3, line.length()));
            if (dir.isDirectory()) {
                prop.setProperty("currentDirectory", dir.toString());
            }
        }
        System.out.println(prop.getProperty("currentDirectory"));
    }

    /**
     * Save server file to client.
     * @param line server file.
     * @throws IOException .
     */
    private void saveToClient(String line) throws IOException {
        FileInputStream fileInStr;
        BufferedInputStream buffInStr;
        File file = new File(prop.getProperty("currentDirectory")
                + "\\" + line.substring(5, line.length()));
        if (file.isFile()) {
            System.out.println(file.toString());
            fileInStr = new FileInputStream(file);
            buffInStr = new BufferedInputStream(fileInStr);
            out.writeLong(file.length());
            out.flush();
            out.writeUTF(file.getName());
            out.flush();
            byte[] buffer = new byte[(int) file.length()];
            buffInStr.read(buffer, 0, buffer.length);
            out.write(buffer);
            out.flush();
        }
    }

    /**
     * Fill properties.
     *
     * @return properties.
     */
    private Properties loadAppProperties() {
        prop = new Properties();
        File dir = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath().substring(1, 4));
        prop.setProperty("currentDirectory", dir.toString());
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            prop.load(inputStream);
            File appProp = new File(classLoader.getResource("app.properties").getFile());
            prop.setProperty("path", appProp.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Main.
     * @param args .
     */
    public static void main(String[] args) {
        new NetworkFileManagerServerImpl().run();
    }
}