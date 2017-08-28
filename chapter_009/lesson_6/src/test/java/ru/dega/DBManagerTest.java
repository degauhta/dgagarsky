package ru.dega;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DBManagerTest class.
 *
 * @author Denis
 * @since 28.08.2017
 */
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
     * Database initialization for testing.
     *
     * @throws SQLException error
     */
    private static void initDatabase() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (LOGIN TEXT, PASSWORD TEXT, "
                    + "NAME TEXT, EMAIL TEXT, CREATEDATE TIMESTAMP, ROLE TEXT)");
            connection.commit();
            statement.execute("INSERT INTO USERS (LOGIN, PASSWORD, NAME, EMAIL, CREATEDATE, ROLE) "
                    + "VALUES ('test-login', 'test-password', 'test-name', 'email', '2017-01-01 00:00:00', 'USER')");
            connection.commit();
        }
    }

    /**
     * Init.
     *
     * @throws NamingException error
     */
    @Before
    public void initJNDI() throws NamingException {
        SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        DataSource dataSource = new DriverManagerDataSource("jdbc:hsqldb:mem:employees",
                "vinod", "vinod");
        builder.bind("java:comp/env/jdbc/mvc", dataSource);
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
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE USERS");
            connection.commit();
        }
    }

    /**
     * Login/password is credential.
     */
    @Test
    public void whenLoginPasswordIsCredentialThenReturnTrue() {
        boolean actual = DBManager.getInstance()
                .isCredential("test-login", "test-password");
        assertThat(actual, is(true));
    }

    /**
     * Login/password is not credential.
     */
    @Test
    public void whenLoginPasswordIsNotCredentialThenReturnTrue() {
        boolean actual = DBManager.getInstance()
                .isCredential("wrong login", "test-password");
        assertThat(actual, is(false));
    }
}