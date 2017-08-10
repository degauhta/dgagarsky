package ru.dega;

import ru.dega.models.User;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DBManager class.
 *
 * @author Denis
 * @since 09.08.2017
 */
@SuppressWarnings("SqlNoDataSourceInspection")
public class DBManager {
    /**
     * Add entry in database.
     *
     * @param connection connection
     * @param user       user
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
     * Get all entries in database.
     *
     * @param connection connection
     * @return list of entries
     */
    public List<String> getAllEntries(Connection connection) {
        List<String> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT * FROM USERS")) {
                while (rs.next()) {
                    String login = rs.getString("LOGIN");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    String createDate = rs.getTimestamp("CREATEDATE").toString();
                    result.add(String.format("login=%s, name=%s, email=%s, created=%s",
                            login, name, email, createDate));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get all entries in database.
     *
     * @param connection connection
     * @param login      login
     * @return list of entries
     */
    public boolean deleteEntry(Connection connection, String login) {
        boolean result = true;
        String sql = "DELETE FROM USERS WHERE login=?;";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get all entries in database.
     *
     * @param connection connection
     * @param login      login
     * @param name       name
     * @param email      email
     * @return list of entries
     */
    public boolean editEntry(Connection connection, String login, String name, String email) {
        boolean result = false;
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE LOGIN=?;";
        try (PreparedStatement preparedStatement = preparedStatement(connection, sql)) {
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