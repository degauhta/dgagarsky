package ru.dega;

import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * DBManagerTest class.
 *
 * @author Denis
 * @since 04.08.2017
 */
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
public class DBManagerTest {
    /**
     * Properties.
     */
    private static Properties prop;

    /**
     * Db name.
     */
    private static final String DB_NAME = "dega_test_servlet_user";

    /**
     * Database manager.
     */
    private DBManager dbManager;

    /**
     * Database connection.
     */
    private Connection connection;

    /**
     * Initialization.
     */
    @BeforeClass
    public static void init() {
        prop = new Properties();
        ClassLoader classLoader = DBManager.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create connection.
     */
    @Before
    public void createConnection() {
        dbManager = new DBManager();
        connection = dbManager.connectToServer(prop.getProperty("dbUrl"),
                prop.getProperty("dbUser"), prop.getProperty("dbPassword"));
    }

    /**
     * Drop database.
     *
     * @throws SQLException error
     */
    @After
    public void dropDatabase() throws SQLException {
        connection = dbManager.connectToServer(prop.getProperty("dbUrl"),
                prop.getProperty("dbUser"), prop.getProperty("dbPassword"));
        String dropConnections = "SELECT pg_terminate_backend(pg_stat_activity.pid) "
                + "FROM pg_stat_activity WHERE pg_stat_activity.datname = 'dega_test_servlet_user' "
                + "AND pid <> pg_backend_pid();";
        Statement statement = connection.createStatement();
        statement.executeQuery(dropConnections);
        String sql = String.format("DROP DATABASE IF EXISTS %s;", DB_NAME);
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * Test connection.
     */
    @Test
    public void whenEqualsConnectionWithNullThenReturnTrue() {
        assertThat((connection != null), is(true));
    }

    /**
     * Test not exists database.
     */
    @Test
    public void whenCheckRandomDataBaseThenReturnFalse() {
        boolean actual = dbManager.checkDBExists(connection,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        assertThat(actual, is(false));
    }

    /**
     * Test create database.
     */
    @Test
    public void whenCheckCreatedDataBaseThenReturnTrue() {
        dbManager.createDB(connection, DB_NAME);
        boolean actual = dbManager.checkDBExists(connection, DB_NAME);
        assertThat(actual, is(true));
    }

    /**
     * Test create table.
     *
     * @throws SQLException error
     */
    @Test
    public void whenCreateSQLTableThenTableExist() throws SQLException {
        dbManager.createDB(connection, DB_NAME);
        String newUrl = String.format("%s%s", prop.getProperty("dbUrl"), DB_NAME);
        connection = dbManager.connectToServer(newUrl,
                prop.getProperty("dbUser"),
                prop.getProperty("dbPassword"));
        dbManager.createTables(connection);
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "users", null);
        boolean actual = tables.next();
        assertThat(actual, is(true));
    }
}