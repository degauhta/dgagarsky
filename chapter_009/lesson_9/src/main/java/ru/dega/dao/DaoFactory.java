package ru.dega.dao;

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DaoFactory class.
 *
 * @author Denis
 * @since 29.08.2017
 */
public class DaoFactory {
    /**
     * Data source.
     */
    private static final PGConnectionPoolDataSource DATA_SOURCE;

    /**
     * Instance.
     */
    public static final DaoFactory INSTANCE = new DaoFactory();

    static {
        Properties prop = new Properties();
        try (InputStream inputStream = DaoFactory.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DATA_SOURCE = new PGConnectionPoolDataSource();
        DATA_SOURCE.setServerName(prop.getProperty("dbHost"));
        DATA_SOURCE.setDatabaseName(prop.getProperty("dbName"));
        DATA_SOURCE.setUser(prop.getProperty("dbUser"));
        DATA_SOURCE.setPassword(prop.getProperty("dbPassword"));
    }

    /**
     * Singleton constructor.
     */
    public DaoFactory() {
    }

    /**
     * Get instance.
     *
     * @return instance
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Get connection.
     *
     * @return a connection to the data source
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}