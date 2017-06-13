package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * FiltersTest class.
 *
 * @author Denis
 * @since 13.06.2017
 */
public class FiltersTest {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Filter.
     */
    private Filters filters;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        String url = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "32167";
        String dbName = "java_from_a_to_z_dega";
        checkDriver();
        connectToServer(String.format("%s%s", url, dbName), user, password);
        this.filters = new Filters(this.connection);
    }

    /**
     * Check postgresql driver.
     */
    private void checkDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Include PostgreSQL JDBC driver in your maven.");
            e.printStackTrace();
        }
    }

    /**
     * Connect to server.
     *
     * @param url      url
     * @param user     user
     * @param password password
     */
    private void connectToServer(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }

    /**
     * Test equally.
     */
    @Test
    public void testEqually() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        this.filters.selectFromUsers("name", "='denis'", true);
        String expected = Joiner.on(System.lineSeparator()).join("ID=2, Name=Denis", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Test like.
     */
    @Test
    public void testLike() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        this.filters.selectFromUsers("name", "like '%den%'", true);
        String expected = Joiner.on(System.lineSeparator()).join("ID=2, Name=Denis", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Test between.
     */
    @Test
    public void testBetween() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        this.filters.selectFromUsers("id", "between 1 and 2", false);
        String expected = Joiner.on(System.lineSeparator()).join("ID=1, Name=Team",
                "ID=2, Name=Denis", "");
        assertThat(outputStream.toString(), is(expected));
    }
}