package ru.job4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * ManagerDB class.
 *
 * @author Denis
 * @since 18.07.2017
 */
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
class ManagerDB {
    /**
     * Properties.
     */
    private Properties prop;

    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Default constructor.
     */
    ManagerDB() {
        readAppProperties();
        String url = prop.getProperty("dbUrl");
        String user = prop.getProperty("dbUser");
        String password = prop.getProperty("dbPassword");
        String dbName = prop.getProperty("dbName");
        connectToServer(url, user, password);
        if (!checkDBExists(dbName)) {
            createDB(dbName);
            connectToServer(String.format("%s%s", url, dbName), user, password);
            createTables();
        } else {
            connectToServer(String.format("%s%s", url, dbName), user, password);
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
     * Check database is exist.
     *
     * @param dbName database name
     * @return true - if exists
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve", "MalformedFormatString"})
    private boolean checkDBExists(String dbName) {
        boolean result = false;
        String sql = "SELECT TRUE AS exist FROM pg_database WHERE datname=?;";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            preparedStatement.setString(1, dbName);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    result = rs.getBoolean("exist");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Create DB.
     *
     * @param dbName DB name.
     */
    private void createDB(String dbName) {
        if (this.connection != null) {
            String sql = String.format("CREATE DATABASE %s", dbName);
            try (PreparedStatement preparedStatement = preparedStatement(sql)) {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection Failed!");
        }
    }

    /**
     * Create tables in DB.
     */
    @SuppressWarnings("SqlNoDataSourceInspection")
    private void createTables() {
        Statement statement;
        String sql;
        try {
            sql = "CREATE TABLE VACANCIES "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " DATE CHARACTER(16) NOT NULL, "
                    + " TOPIC CHARACTER(1000) NOT NULL, "
                    + " FULLDESCRIPTION TEXT NOT NULL, "
                    + " URL CHARACTER(1000) NOT NULL);";
            statement = this.connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add entry in database.
     *
     * @param topicDate       topic date
     * @param topic           topic name
     * @param fullDescription topic full description
     * @param url             topic url
     * @return true if added successfully
     */
    boolean addEntry(String topicDate, String topic, String fullDescription, String url) {
        fullDescription = fullDescription.replace("\n", "").replace("\r", "");
        boolean result = findEntry(topicDate, topic, url);
        if (!result) {
            String sql = "INSERT INTO VACANCIES (DATE, TOPIC, FULLDESCRIPTION, URL) "
                    + "VALUES (?, ?, ?, ?);";
            try (PreparedStatement preparedStatement = preparedStatement(sql)) {
                preparedStatement.setString(1, topicDate);
                preparedStatement.setString(2, topic);
                preparedStatement.setString(3, fullDescription);
                preparedStatement.setString(4, url);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return !result;
    }

    /**
     * Find entry in database by date and topic name.
     *
     * @param topic     topic name
     * @param topicDate topic date
     * @param url       topic url
     * @return true if find topic in database
     */
    private boolean findEntry(String topicDate, String topic, String url) {
        boolean result = true;
        String sql = "SELECT * FROM VACANCIES WHERE DATE = ? and TOPIC = ? and URL = ?";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            preparedStatement.setString(1, topicDate);
            preparedStatement.setString(2, topic);
            preparedStatement.setString(3, url);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    result = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Read properties.
     */
    @SuppressWarnings("ConstantConditions")
    private void readAppProperties() {
        prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            this.prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Precompile SQL statement.
     *
     * @param sql sql string
     * @return prepared statement
     */
    private PreparedStatement preparedStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}