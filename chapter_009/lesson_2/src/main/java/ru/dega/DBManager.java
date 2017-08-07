package ru.dega;

import ru.dega.models.User;

import java.io.InputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;

/**
 * DBManager class.
 *
 * @author Denis
 * @since 04.08.2017
 */
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class DBManager {
    /**
     * Start.
     *
     * @param classLoader class loader
     * @throws IOException error
     */
    public void start(ClassLoader classLoader) throws IOException {
        Properties prop = readAppProperties(classLoader);
        String dbUrl = prop.getProperty("dbUrl");
        String dbUser = prop.getProperty("dbUser");
        String dbPassword = prop.getProperty("dbPassword");
        String dbName = prop.getProperty("dbName");
        Connection connection = connectToServer(dbUrl, dbUser, dbPassword);
        if (connection != null && !checkDBExists(connection, dbName)) {
            createDB(connection, dbName);
            connection = connectToServer(String.format("%s%s", dbUrl, dbName), dbUser, dbPassword);
            createTables(connection);
        }
    }

    /**
     * Read app properties.
     *
     * @param classLoader class loader
     * @return properties
     */
    private Properties readAppProperties(ClassLoader classLoader) {
        Properties prop = new Properties();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Connect to server.
     *
     * @param dbUrl      Url
     * @param dbUser     User
     * @param dbPassword Password
     * @return connection
     */
    Connection connectToServer(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * Check database is exist.
     *
     * @param connection connection
     * @param dbName     database name
     * @return true - if exists
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve", "MalformedFormatString"})
    boolean checkDBExists(Connection connection, String dbName) {
        boolean result = false;
        String sql = "SELECT TRUE AS exist FROM pg_database WHERE datname=?;";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
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
     * @param connection connection
     * @param dbName     DB name.
     */
    void createDB(Connection connection, String dbName) {
        String sql = String.format("CREATE DATABASE %s", dbName);
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create tables in DB.
     *
     * @param connection connection
     */
    void createTables(Connection connection) {
        Statement statement;
        String sql;
        try {
            sql = "CREATE TABLE IF NOT EXISTS USERS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(1000) NOT NULL, "
                    + " LOGIN CHARACTER(1000) NOT NULL, "
                    + " EMAIL CHARACTER(1000) NOT NULL, "
                    + " CREATEDATE TIMESTAMP NOT NULL);";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Precompile SQL statement.
     *
     * @param connection connection
     * @param sql        sql string
     * @return prepared statement
     */
    private PreparedStatement preparedStatement(Connection connection, String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Add entry in database.
     *
     * @param connection connection
     * @param user user
     * @return true if added successfully
     */
    public boolean addEntry(Connection connection, User user) {
        boolean result = true;
        String sql = "INSERT INTO USERS (NAME, LOGIN, EMAIL, CREATEDATE) "
                + "VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setTimestamp(4,
                    Timestamp.valueOf(user.getCreateDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find entry in database by name, login and email.
     *
     * @param connection connection
     * @param name  name
     * @param login login
     * @param email email
     * @return true if find in database
     */
    public boolean findEntry(Connection connection, String name, String login, String email) {
        boolean result = false;
        String sql = "SELECT * FROM USERS WHERE NAME = ? and LOGIN = ? and EMAIL = ?";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, email);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Edit entry in database.
     *
     * @param userData user data
     * @param connection connection
     * @return true if edit successfully
     */
    public boolean editEntry(Connection connection, Map<String, String> userData) {
        boolean result = false;
        String sql = "UPDATE USERS SET NAME = ?, LOGIN = ?, EMAIL=? "
                + "WHERE NAME = ? AND LOGIN = ? AND EMAIL=?";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
            preparedStatement.setString(1, userData.get("newName"));
            preparedStatement.setString(2, userData.get("newLogin"));
            preparedStatement.setString(3, userData.get("newEmail"));
            preparedStatement.setString(4, userData.get("name"));
            preparedStatement.setString(5, userData.get("login"));
            preparedStatement.setString(6, userData.get("email"));
            if (preparedStatement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete entry in database.
     *
     * @param userData user data
     * @param connection connection
     * @return true if edit successfully
     */
    public boolean deleteEntry(Connection connection, Map<String, String> userData) {
        boolean result = false;
        String sql = "DELETE FROM USERS WHERE NAME = ? AND LOGIN = ? AND EMAIL=?";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
            preparedStatement.setString(1, userData.get("name"));
            preparedStatement.setString(2, userData.get("login"));
            preparedStatement.setString(3, userData.get("email"));
            if (preparedStatement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}