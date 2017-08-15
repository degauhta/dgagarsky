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
    private DataSource ds;

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

    /**
     * Get connection from data source.
     *
     * @return connection
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            if (this.ds == null) {
                initializeDataSource();
            }
            connection = this.ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Data source initialization.
     */
    private void initializeDataSource() {
        try {
            InitialContext initContext = new InitialContext();
            this.ds = (DataSource) initContext.lookup(
                    "java:comp/env/jdbc/mvc");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all entries in database.
     *
     * @return list of entries
     */
    public List<User> getAllEntries() {
        List<User> result = new ArrayList<>();
        try (Connection con = getConnection(); Statement statement = con.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT * FROM USERS")) {
                while (rs.next()) {
                    result.add(new User(rs.getString("LOGIN"),
                            rs.getString("PASSWORD"),
                            rs.getString("NAME"),
                            rs.getString("EMAIL"),
                            rs.getTimestamp("CREATEDATE").toLocalDateTime(),
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
        String sql = "INSERT INTO USERS (LOGIN, PASSWORD, NAME, EMAIL, CREATEDATE, ROLE) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setTimestamp(5,
                    Timestamp.valueOf(user.getCreateDate()));
            preparedStatement.setString(6, user.getRole().toString());
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
        try (Connection con = getConnection();
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
     * @param login login
     * @param name  new name
     * @param email new email
     * @return true if edit successfully
     */
    public boolean editEntry(String login, String name, String email) {
        boolean result = false;
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE LOGIN=?;";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, login);
            if (preparedStatement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Check login and password.
     *
     * @param login login
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
     * Get role.
     *
     * @param login login
     * @return user role
     */
    public UserRole getUserRole(String login) {
        UserRole role = null;
        String sql = "SELECT * FROM USERS VALUES WHERE LOGIN=?;";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = preparedStatement(con, sql)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    role = UserRole.valueOf(rs.getString("ROLE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
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