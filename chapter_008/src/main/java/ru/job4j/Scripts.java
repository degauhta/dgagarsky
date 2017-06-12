package ru.job4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Scripts class.
 *
 * @author Denis
 * @since 08.06.2017
 */
class Scripts {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Default constructor.
     */
    Scripts() {
        checkDriver();
    }

    /**
     * Check postgresql driver.
     */
    private void checkDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Include PostgreSQL JDBC driver in your maven.");
            e.printStackTrace();
        }
    }

    /**
     * Connect to server.
     *
     * @param url      url
     * @param user     user
     * @param password password
     */
    void connectToServer(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(
                    url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }

    /**
     * Create DB.
     *
     * @param dbName DB name.
     */
    void createDB(String dbName) {
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
    void createTables() {
        Statement statement;
        String sql;
        try {
            sql = "CREATE TABLE USERS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(2000) NOT NULL);"

                    + "CREATE TABLE ROLES "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(2000) NOT NULL);"

                    + "CREATE TABLE RIGHTS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " ADD_TICKET BOOLEAN NOT NULL, "
                    + " ADD_COMMENT BOOLEAN NOT NULL, "
                    + " DELETE_TICKET BOOLEAN NOT NULL, "
                    + " DELETE_COMMENT BOOLEAN NOT NULL);"

                    + "CREATE TABLE USER_ROLE "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " USER_ID INTEGER REFERENCES USERS(ID), "
                    + " ROLE_ID INTEGER REFERENCES ROLES(ID));"

                    + "CREATE TABLE ROLE_RIGHT "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " ROLE_ID INTEGER REFERENCES ROLES(ID), "
                    + " RIGHT_ID INTEGER REFERENCES RIGHTS(ID));"

                    + "CREATE TABLE TICKETS_TYPES "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(200) NOT NULL);"

                    + "CREATE TABLE TICKETS_STATUS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(200) NOT NULL);"

                    + "CREATE TABLE TICKETS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(2000) NOT NULL, "
                    + " USER_ID INTEGER REFERENCES USERS(ID), "
                    + " TICKETS_TYPES_ID INTEGER REFERENCES TICKETS_TYPES(ID), "
                    + " TICKETS_STATUS_NAME INTEGER REFERENCES TICKETS_STATUS(ID));"

                    + "CREATE TABLE TICKET_COMMENTS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " COMMENT CHARACTER(200) NOT NULL, "
                    + " TICKETS_ID INTEGER REFERENCES TICKETS(ID));"

                    + "CREATE TABLE TICKET_FILES "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " TICKETS_ID INTEGER REFERENCES TICKETS(ID));";

            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert test data in tables.
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
    void insertTestData() {
        Statement statement;
        String sql;
        try {
            statement = connection.createStatement();
            sql = "INSERT INTO USERS (NAME) "
                    + "VALUES ('Team Lead'), ('Denis'), ('worker1');"

                    + "INSERT INTO ROLES (NAME) "
                    + "VALUES ('Administrator'), ('Moderator'), ('User');"

                    + "INSERT INTO USER_ROLE (USER_ID, ROLE_ID) "
                    + "VALUES (1, 1), (2, 2), (3, 3);"

                    + "INSERT INTO RIGHTS (ADD_TICKET, ADD_COMMENT, DELETE_TICKET, DELETE_COMMENT) "
                    + "VALUES (TRUE, FALSE, FALSE, FALSE), (FALSE, TRUE, FALSE, FALSE), "
                    + "(TRUE, FALSE, FALSE, FALSE), (FALSE, TRUE, FALSE, FALSE);"

                    + "INSERT INTO ROLE_RIGHT (ROLE_ID, RIGHT_ID) "
                    + "VALUES (1, 1), (1, 2), (1, 3), (1, 4);"

                    + "INSERT INTO TICKETS_TYPES (NAME) "
                    + "VALUES ('Task'), ('Bug');"

                    + "INSERT INTO TICKETS_STATUS (NAME) "
                    + "VALUES ('Open'), ('Closed');"

                    + "INSERT INTO TICKETS (NAME, USER_ID, TICKETS_TYPES_ID, TICKETS_STATUS_NAME) "
                    + "VALUES ('Error connection', 3, 2, 1);"

                    + "INSERT INTO TICKET_COMMENTS (COMMENT, TICKETS_ID) "
                    + "VALUES ('Fixed.', 1);";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
