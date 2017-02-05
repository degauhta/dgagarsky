package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * NetworkFileManagerClientImplTest class.
 *
 * @author Denis
 * @since 29.01.2017
 */
public class NetworkFileManagerClientImplTest {
    /**
     * Client.
     */
    private NetworkFileManagerClientImpl client;

    /**
     * Socket.
     */
    private Socket socket;

    /**
     * New line.
     */
    private static final String LN = System.getProperty("line.separator");

    /**
     * Initialization.
     */
    @Before
    public void init() {
        this.client = new NetworkFileManagerClientImpl();
        this.socket = mock(Socket.class);
    }

    /**
     * Test quit.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenInputQuitThenQuit() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream arrayIn = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(arrayIn);

        String s = Joiner.on(LN).join("123", "quit");
        InputStream in = new ByteArrayInputStream(s.getBytes());
        data.writeUTF("C:\\");
        data.writeUTF("C:\\");
        data.writeUTF("C:\\");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(arrayIn.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.client.executeCommand(this.socket, in);
        verify(this.socket).getOutputStream();
        verify(this.socket).getInputStream();
    }

    /**
     * Test dir.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenInputDirThenPrintDirectories() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream arrayIn = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(arrayIn);

        String s = Joiner.on(LN).join("dir", "quit");
        InputStream in = new ByteArrayInputStream(s.getBytes());
        data.writeUTF("C:\\");
        data.writeUTF("C:\\1");
        data.writeUTF("endOfDir");
        data.writeUTF("C:\\1");
        data.writeUTF("C:\\1");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(arrayIn.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.client.executeCommand(this.socket, in);
        verify(this.socket).getOutputStream();
        verify(this.socket).getInputStream();
    }

    /**
     * Test dir.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenLoadThenLoadFile() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream arrayIn = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(arrayIn);

        String s = Joiner.on(LN).join("load", "quit");
        InputStream in = new ByteArrayInputStream(s.getBytes());
        data.writeUTF("C:\\");
        data.writeUTF("loaded");
        data.writeUTF("C:\\1");
        data.writeUTF("C:\\1");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(arrayIn.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        System.setOut(new PrintStream(data));
        this.client.executeCommand(this.socket, in);

        FileInputStream fileInStr;
        File file = new File(NetworkFileManagerClientImpl.class.getClassLoader().getResource("clientFile.txt").getFile());
        fileInStr = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        fileInStr.read(buffer, 0, buffer.length);

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(arrayOut.toByteArray()));
        assertThat(dis.readUTF(), is("load"));
        assertThat(dis.readLong(), is(file.length()));

        byte[] bufferDis = new byte[(int) file.length()];
        dis.read(bufferDis, 0, bufferDis.length);
        assertThat(bufferDis, is(buffer));
    }

    /**
     * Test dir.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenSaveThenSaveFile() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream arrayIn = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(arrayIn);

        String s = Joiner.on(LN).join("save 1.txt", "quit");
        InputStream in = new ByteArrayInputStream(s.getBytes());

        FileInputStream fileInStr;
        File file = new File(getClass().getClassLoader().getResource("clientNetFileManTestFile.txt").getFile());
        fileInStr = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        fileInStr.read(buffer, 0, buffer.length);

        data.writeUTF("C:\\");
        data.writeLong(file.length());
        data.writeUTF("file name.txt");
        data.write(buffer);
        data.writeUTF("C:\\");
        data.writeUTF("C:\\");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(arrayIn.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.client.executeCommand(this.socket, in);
        verify(this.socket).getOutputStream();
        verify(this.socket).getInputStream();
    }
}