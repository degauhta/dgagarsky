package ru.job4j;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * NetworkFileManagerServerImplTest class.
 *
 * @author Denis
 * @since 29.01.2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DataInputStream.class)
public class NetworkFileManagerServerImplTest {
    /**
     * Test.
     */
    @Test
    public void whenThen() throws IOException {
        NetworkFileManagerServerImpl server = new NetworkFileManagerServerImpl();
        //DataInputStream in = PowerMockito.mock(DataInputStream.class);
        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        Properties prop = Mockito.mock(Properties.class);
        Socket socket = Mockito.mock(Socket.class);
        /**/

//        final DataInputStream in = PowerMockito.mock(DataInputStream.class);
//        PowerMockito.mockStatic(DataInputStream.class);

//        DataInputStream in = mock(DataInputStream.class);
//        when(in.readUTF()).thenReturn("quit");
//        Mockito.when(in.readUTF()).thenReturn("quit");

        DataInputStream in = PowerMockito.mock(DataInputStream.class);
//        PowerMockito.mockStatic(DataInputStream.class);
//        Mockito.doNothing().when(in).readUTF();


        /**/
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        when(prop.getProperty("currentDirectory")).thenReturn("c:\\");
//        PowerMockito.when(in.read()).thenReturn(4);
//        PowerMockito.when(in.readUTF()).thenReturn("quit");

//        PowerMockito.when(in.readUTF()).thenThrow(new IOException("test"));

        server.executeCommand(socket, prop);

//        verify(socket).getOutputStream();
//        verify(socket).getInputStream();
//        verify(prop).getProperty("currentDirectory");
//        verify(in).readUTF();
    }

}