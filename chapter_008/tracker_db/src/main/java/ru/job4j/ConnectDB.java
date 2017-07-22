package ru.job4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Created by dega on 25.06.2017.
 */
@SuppressWarnings("SqlNoDataSourceInspection")
class ConnectDB {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Get connection.
     *
     * @return connection
     */
    Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "32167";
        String dbName = "tracker_dega";
        connectToServer(url, user, password);
        if (!checkDBExists(dbName)) {
            createDB(dbName);
            connectToServer(String.format("%s%s", url, dbName), user, password);
            createTables();
        } else {
            connectToServer(String.format("%s%s", url, dbName), user, password);
        }
        return this.connection;
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
        try {
            String sql = "SELECT TRUE AS exist FROM pg_database WHERE datname=?;";
            PreparedStatement preparedStatement = preparedStatement(sql);
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
        String sql;
        try (Statement statement = this.connection.createStatement()) {
            sql = "CREATE TABLE TASKS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME TEXT NOT NULL, "
                    + " DESCRIPTION TEXT NOT NULL);";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
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