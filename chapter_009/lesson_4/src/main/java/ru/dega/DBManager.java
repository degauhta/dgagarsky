package ru.dega;

import ru.dega.models.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DBManager class.
 *
 * @author Denis
 * @since 11.08.2017
 */
@SuppressWarnings({"Duplicates", "SqlNoDataSourceInspection"})
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
                    "java:comp/env/jdbc/jsp");
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
                    String login = rs.getString("LOGIN");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    LocalDateTime createDate = rs.getTimestamp("CREATEDATE").toLocalDateTime();
                    result.add(new User(name, login, email, createDate));
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
        String sql = "INSERT INTO USERS (NAME, LOGIN, EMAIL, CREATEDATE) "
                + "VALUES (?, ?, ?, ?);";
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = preparedStatement(con, sql)) {
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