package ru.dega;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.dega.models.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DBManagerTest class.
 *
 * @author Denis
 * @since 08.08.2017
 */
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
public class DBManagerTest {
    /**
     * Before class.
     *
     * @throws SQLException           error
     * @throws ClassNotFoundException error
     * @throws IOException            error
     */
    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        initDatabase();
    }

    /**
     * After.
     *
     * @throws SQLException           error
     * @throws ClassNotFoundException error
     * @throws IOException            error
     */
    @AfterClass
    public static void destroy() throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.executeUpdate("DROP TABLE USERS");
            connection.commit();
        }
    }

    /**
     * Create a connection.
     *
     * @return connection object
     */
    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:hsqldb:mem:employees;sql.syntax_pgs = true",
                            "vinod", "vinod");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Database initialization for testing i.e.
     * <ul>
     * <li>Creating Table</li>
     * <li>Inserting record</li>
     * </ul>
     *
     * @throws SQLException error
     */
    private static void initDatabase() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (NAME TEXT,"
                    + "LOGIN TEXT, EMAIL TEXT, CREATEDATE TIMESTAMP)");
            connection.commit();
        }
    }

    /**
     * Clear database.
     *
     * @throws SQLException error
     */
    @After
    public void clearDB() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            statement.execute("DELETE from USERS");
            connection.commit();
        }
    }

    /**
     * Test add entry.
     */
    @Test
    public void whenSuccessfullyAddedEntryThenReturnTrue() {
        DBManager dbManager = new DBManager();
        boolean actual = dbManager.addEntry(getConnection(), new User("name", "login", "mail"));
        assertThat(actual, is(true));
    }

    /**
     * Test get entries.
     *
     * @throws SQLException error
     */
    @Test
    public void whenSuccessfullyGetEntriesThenReturnListOfEntries() throws SQLException {
        User user = new User("name", "login", "mail");
        DBManager dbManager = new DBManager();
        List<String> expected = new ArrayList<>();
        expected.add(String.format("login=login, name=name, email=mail, created=%s",
                Timestamp.valueOf(user.getCreateDate())));
        dbManager.addEntry(getConnection(), user);
        List<String> actual = dbManager.getAllEntries(getConnection());
        assertThat(actual, is(expected));
    }

    /**
     * Test delete entry.
     */
    @Test
    public void whenSuccessfullyDeletedEntryThenReturnTrue() {
        DBManager dbManager = new DBManager();
        dbManager.addEntry(getConnection(),
                new User("name", "login", "mail"));
        boolean actual = dbManager.deleteEntry(getConnection(), "login");
        assertThat(actual, is(true));
    }

    /**
     * Test edit entry.
     */
    @Test
    public void whenSuccessfullyEditEntryThenReturnTrue() {
        DBManager dbManager = new DBManager();
        dbManager.addEntry(getConnection(),
                new User("name", "login", "mail"));
        boolean actual = dbManager.editEntry(getConnection(),
                "login", "new-name", "new-mail");
        assertThat(actual, is(true));
    }
}