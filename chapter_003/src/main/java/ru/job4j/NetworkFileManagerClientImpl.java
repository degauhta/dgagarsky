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

import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * NetworkFileManagerServerImpl class.
 *
 * @author Denis
 * @since 24.01.2017
 */
public class NetworkFileManagerClientImpl implements NetworkFileManagerClient {
    /**
     * Properties.
     */
    private Properties prop = loadAppProperties();

    /**
     * Input socket stream.
     */
    private DataInputStream in;

    /**
     * Output socket stream.
     */
    private DataOutputStream out;

    /**
     * Running client.
     * Read user command from input stream.
     */
    @Override
    public void run() {
        System.out.println("commands:");
        System.out.println("cd 'name subfolder' - change current folder to 'name' subfolder");
        System.out.println("cd '..' - change to the parent directory");
        System.out.println("dir - display a list of files and folders");
        System.out.println("load - load file to server");
        System.out.println("save 'name.extension' - save file from server");
        System.out.println();
        try (Socket socket = new Socket(prop.getProperty("server"),
                Integer.parseInt(prop.getProperty("port")))) {
            executeCommand(socket, System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute commands from console.
     * @param socket server socket.
     * @param scannerStream input stream for scanner.
     * @throws IOException io error.
     */
    public void executeCommand(Socket socket, InputStream scannerStream) throws IOException {
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        System.out.printf("%s> ", in.readUTF());
        Scanner scanner = new Scanner(scannerStream);
        String command;

        do {
            command = scanner.nextLine();
            out.writeUTF(command);
            out.flush();

            if ("dir".equals(command)) {
                printCatalogs();
            }

            if ("load".equals(command)) {
                loadFile();
            }

            if (command.length() > 5 && "save ".equals(command.substring(0, 5))) {
                saveFile();
            }

            System.out.printf("%s> ", in.readUTF());
        } while (!"quit".equals(command));
    }

    /**
     * Save file from server.
     *
     * @throws IOException .
     */
    @Override
    public void saveFile() throws IOException {
        FileOutputStream fileOutStr;
        BufferedOutputStream buffOutStr;
        long fileSize = in.readLong();
        String fileName = in.readUTF();
        byte[] buffer = new byte[(int) fileSize];

        fileOutStr = new FileOutputStream(prop.getProperty("path") + "\\" + fileName);
        buffOutStr = new BufferedOutputStream(fileOutStr);

        in.read(buffer, 0, buffer.length);
        buffOutStr.write(buffer);
        buffOutStr.flush();
    }

    /**
     * Load file to server.
     *
     * @throws IOException .
     */
    @Override
    public void loadFile() throws IOException {
        FileInputStream fileInStr;
        BufferedInputStream buffInStr;
        File file = new File(getClass().getClassLoader().getResource("clientFile.txt").getFile());
        fileInStr = new FileInputStream(file);
        buffInStr = new BufferedInputStream(fileInStr);
        out.writeLong(file.length());
        out.flush();
        byte[] buffer = new byte[(int) file.length()];
        buffInStr.read(buffer, 0, buffer.length);
        out.write(buffer);
        out.flush();
        System.out.println(in.readUTF());
    }

    /**
     * Print current catalogs.
     *
     * @throws IOException .
     */
    @Override
    public void printCatalogs() throws IOException {
        String answer;
        try {
            do {
                answer = in.readUTF();
                System.out.println(answer);
            } while (!"endOfDir".equals(answer));
        } catch (EOFException e) {
            System.out.println("end");
        }
    }

    /**
     * Fill properties.
     *
     * @return properties.
     */
    private Properties loadAppProperties() {
        prop = new Properties();
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
     *
     * @param args .
     */
    public static void main(String[] args) {
        new NetworkFileManagerClientImpl().run();
    }
}
