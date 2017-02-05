package ru.job4j;

import org.junit.Before;
import org.junit.Test;

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
 * NetworkFileManagerServerImplTest class.
 *
 * @author Denis
 * @since 29.01.2017
 */
//@RunWith(PowerMockRunner.class)
public class NetworkFileManagerServerImplTest {
    /**
     * Sever.
     */
    private NetworkFileManagerServerImpl server;

    /**
     * Socket.
     */
    private Socket socket;

    /**
     * Current path.
     */
    private String currentPath;

    /**
     * Init.
     */
    @Before
    public void init() {
        this.server = new NetworkFileManagerServerImpl();
        this.socket = mock(Socket.class);
        this.currentPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath().substring(1, 3) + "\\";
    }

    /**
     * Test input quit.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenInputQuitThenQuit() throws IOException {
        DataOutputStream out = mock(DataOutputStream.class);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(array);
        data.writeUTF("quit");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(array.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.server.executeCommand(this.socket);

        verify(this.socket).getOutputStream();
        verify(this.socket).getInputStream();
    }

    /**
     * Test cd.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenInputCdThenGetNewDirectory() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(array);

        File newDir;
        do {
            newDir = new File(this.currentPath + System.currentTimeMillis());
        } while (newDir.exists());
        newDir.mkdir();
        data.writeUTF("cd " + newDir.getName());
        data.writeUTF("quit");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(array.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.server.executeCommand(this.socket);

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(arrayOut.toByteArray()));

        assertThat(dis.readUTF(), is(this.currentPath));
        assertThat(dis.readUTF(), is(this.currentPath + newDir.getName()));
        assertThat(dis.readUTF(), is(this.currentPath + newDir.getName()));
        newDir.delete();
    }

    /**
     * Test dir.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenInputDirThenGetListOfDirectories() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(array);

        File newDir;
        do {
            newDir = new File(this.currentPath + System.currentTimeMillis());
        } while (newDir.exists());
        newDir.mkdir();
        File newSubDir;
        do {
            newSubDir = new File(newDir.toString() + "\\" + System.currentTimeMillis());
        } while (newSubDir.exists());
        newSubDir.mkdir();
        data.writeUTF("cd " + newDir.getName());
        data.writeUTF("dir");
        data.writeUTF("quit");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(array.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.server.executeCommand(this.socket);

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(arrayOut.toByteArray()));
        assertThat(dis.readUTF(), is(this.currentPath));
        assertThat(dis.readUTF(), is(newDir.getAbsolutePath()));
        assertThat(dis.readUTF(), is(newSubDir.getAbsolutePath()));
        assertThat(dis.readUTF(), is("endOfDir"));
        assertThat(dis.readUTF(), is(newDir.getAbsolutePath()));
        assertThat(dis.readUTF(), is(newDir.getAbsolutePath()));
        newSubDir.delete();
        newDir.delete();
    }

    /**
     * Test load.
     * @throws IOException io error.
     */
    @Test
    public void whenInputLoadThenLoadFile() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(array);

        data.writeUTF("load");

        FileInputStream fileInStr;
        File file = new File(getClass().getClassLoader().getResource("clientNetFileManTestFile.txt").getFile());
        fileInStr = new FileInputStream(file);
        data.writeLong(file.length());
        byte[] buffer = new byte[(int) file.length()];
        fileInStr.read(buffer, 0, buffer.length);
        data.write(buffer);

        data.writeUTF("quit");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(array.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.server.executeCommand(this.socket);

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(arrayOut.toByteArray()));

        assertThat(dis.readUTF(), is(this.currentPath));
        assertThat(dis.readUTF(), is("loaded"));
        assertThat(dis.readUTF(), is(this.currentPath));
        assertThat(dis.readUTF(), is(this.currentPath));
    }

    /**
     * Test dir.
     *
     * @throws IOException io error.
     */
    @Test
    public void whenInputSaveThenSaveFile() throws IOException {
        ByteArrayOutputStream arrayOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(arrayOut);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(array);

        File newDir;
        do {
            newDir = new File(this.currentPath + System.currentTimeMillis());
        } while (newDir.exists());
        newDir.mkdir();
        File newFile;
        newFile = new File(newDir.toString() + "\\321.txt");
        newFile.createNewFile();
        data.writeUTF("save " + newFile.getAbsolutePath().substring(3, newFile.getAbsolutePath().length()));
        data.writeUTF("quit");
        data.close();

        when(this.socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(array.toByteArray())));
        when(this.socket.getOutputStream()).thenReturn(out);

        this.server.executeCommand(this.socket);

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(arrayOut.toByteArray()));
        assertThat(dis.readUTF(), is(this.currentPath));

        assertThat(dis.readLong(), is(newFile.length()));
        assertThat(dis.readUTF(), is(newFile.getName()));

        byte[] buffer = new byte[(int) newFile.length()];
        byte[] bufferNet = new byte[(int) newFile.length()];
        FileInputStream fileInStr = new FileInputStream(newFile);
        assertThat(dis.read(bufferNet, 0, buffer.length), is(fileInStr.read(buffer, 0, buffer.length)));

        assertThat(dis.readUTF(), is(this.currentPath));
        assertThat(dis.readUTF(), is(this.currentPath));

        fileInStr.close();
        dis.close();
    }
}