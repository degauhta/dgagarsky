package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * UserStorageTest class.
 *
 * @author Denis
 * @since 12.04.2017
 */
public class UserStorageTest {
    /**
     * User 1.
     */
    private User user1;

    /**
     * User 2.
     */
    private User user2;

    /**
     * User 3.
     */
    private User user3;

    /**
     * User storage.
     */
    private UserStorage<User> storage;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        user1 = new User("1", 100);
        user2 = new User("2", 200);
        user3 = new User("3", 300);
        storage = new UserStorage<>();
        storage.add(user1);
        storage.add(user2);
        storage.add(user3);
    }

    /**
     * Test transfer.
     */
    @Test
    public void whenTransferAllMoneyToUser3ThenAllMoneyBelongsToUser3() {
        storage.transfer(user1, user2, 100);
        storage.transfer(user2, user3, 300);
        assertThat(user3.getAmount(), is(600));
    }

    /**
     * Test failed transfer.
     */
    @Test
    public void whenTransferFromUserWithoutMoneyThenMessage() {
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        storage.transfer(user1, user2, 100);
        storage.transfer(user1, user3, 300);
        String expected = Joiner.on(System.lineSeparator()).join("Not enough money", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Test storage size.
     */
    @Test
    public void whenGetSizeOfStorageWith3ElementThenReturn3() {
        assertThat(storage.getSize(), is(3));
    }

    /**
     * Test print.
     */
    @Test
    public void whenPrintSrtorageThenMessageWithAllElementInfo() {
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        storage.print();
        String expected = Joiner.on(System.lineSeparator()).join("1 - 100",
                "2 - 200", "3 - 300", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDelete1ElementFromStorageWith3ElementsThenSizeIs2() {
        storage.delete(user3);
        assertThat(storage.getSize(), is(2));
    }
}