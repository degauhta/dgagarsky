package ru.job4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by dega on 25.06.2017.
 */
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
        try (Statement statement = this.connection.createStatement()) {
            String sql = "SELECT TRUE AS exist FROM pg_database WHERE datname='" + dbName + "';";
            try (ResultSet rs = statement.executeQuery(sql)) {
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
        Statement statement;
        if (this.connection != null) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(String.format("CREATE DATABASE %s", dbName));
                statement.close();
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
            sql = "CREATE TABLE TASKS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(1000) NOT NULL, "
                    + " DESCRIPTION CHARACTER(1000) NOT NULL);";
            statement = this.connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}