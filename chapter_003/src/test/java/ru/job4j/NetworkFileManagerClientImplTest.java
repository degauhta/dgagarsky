package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * NetworkFileManagerClientImplTest class.
 *
 * @author Denis
 * @since 29.01.2017
 */
public class NetworkFileManagerClientImplTest {
    /**
     *
     */
    @Test
    public void whenThen() {
        NetworkFileManagerClient client = new NetworkFileManagerClientImpl();
        NetworkFileManagerServerImpl server = mock(NetworkFileManagerServerImpl.class);

    }
}