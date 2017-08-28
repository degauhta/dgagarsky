package ru.dega;

import ru.dega.models.User;
import ru.dega.models.UserRole;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DBManager class.
 *
 * @author Denis
 * @since 11.08.2017
 */
@SuppressWarnings({"Duplicates", "SqlNoDataSourceInspection", "SqlDialectInspection"})
public class DBManager {
    /**
     * Data source.
     */
    private static final DataSource DATA_SOURCE;

    /**
     * Instance.
     */
    public static final DBManager INSTANCE = new DBManager();

    /**
     * Singleton constructor.
     */
    public DBManager() {
    }

    /**
     * Get instance.
     *
     * @return instance
     */
    public static DBManager getInstance() {
        return INSTANCE;
    }

    static {
        try {
            InitialContext initContext = new InitialContext();
            DATA_SOURCE = (DataSource) initContext.lookup(
                    "java:comp/env/jdbc/mvc");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("dataSource not initialized");
        }
    }

    /**
     * Get all entries in database.
     *
     * @return list of entries
     */
    public List<User> getAllEntries() {
        List<User> result = new ArrayList<>();
        try (Connection con = DATA_SOURCE.getConnection(); Statement statement = con.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT * FROM USERS")) {
                while (rs.next()) {
                    result.add(new User(rs.getString("LOGIN"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getString("EMAIL"),
                            rs.getTimestamp("CREATEDATE").toLocalDateTime(),
                            rs.getString("COUNTRY"),
                            rs.getString("TOWN"),
                            UserRole.valueOf(rs.getString("ROLE"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Add entry in database.
     *
     * @param user user
     * @return true if added successfully
     */
    public boolean addEntry(User user) {
        boolean result = true;
        String sql = "INSERT INTO USERS (LOGIN, PASSWORD, NAME, EMAIL, CREATEDATE, COUNTRY, TOWN, ROLE) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection con = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setTimestamp(5,
                    Timestamp.valueOf(user.getCreateDate()));
            preparedStatement.setString(6, user.getCountry());
            preparedStatement.setString(7, user.getTown());
            preparedStatement.setString(8, user.getRole().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete entry in database.
     *
     * @param login login
     * @return list of entries
     */
    public boolean deleteEntry(String login) {
        boolean result = true;
        String sql = "DELETE FROM USERS WHERE login=?;";
        try (Connection con = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Edit entry in database.
     *
     * @param login   login
     * @param name    new name
     * @param email   new email
     * @param country country
     * @param town    town
     * @return true if edit successfully
     */
    public boolean editEntry(String login, String name, String email, String country, String town) {
        boolean result = false;
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ?, COUNTRY = ?, TOWN = ? WHERE LOGIN=?;";
        try (Connection con = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, country);
            preparedStatement.setString(4, town);
            preparedStatement.setString(5, login);
            if (preparedStatement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get entry form database.
     *
     * @param login login
     * @return true if edit successfully
     */
    public User getEntry(String login) {
        User result = null;
        String sql = "SELECT * FROM USERS WHERE LOGIN=?;";
        try (Connection con = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    result = new User(rs.getString("LOGIN"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getString("EMAIL"),
                            null,
                            rs.getString("COUNTRY"),
                            rs.getString("TOWN"),
                            UserRole.valueOf(rs.getString("ROLE")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Check login and password.
     *
     * @param login    login
     * @param password password
     * @return true if credential
     */
    public boolean isCredential(String login, String password) {
        boolean exist = false;
        for (User user : getAllEntries()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exist = true;
                break;
            }
        }
        return exist;
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
}