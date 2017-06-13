package ru.job4j;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Filters class.
 *
 * @author Denis
 * @since 13.06.2017
 */
class Filters {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Default constructor.
     *
     * @param connection connection
     */
    Filters(Connection connection) {
        this.connection = connection;
    }

    /**
     * Select filed from users table.
     *
     * @param filed     filed
     * @param condition condition
     * @param lowerCase lowerCase
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve", "MalformedFormatString"})
    void selectFromUsers(String filed, String condition, boolean lowerCase) {
        Statement statement;
        String sql;
        String name;
        try {
            statement = connection.createStatement();
            if (lowerCase) {
                sql = String.format("SELECT * FROM USERS AS i WHERE LOWER(i.%s) %s;", filed, condition);
            } else {
                sql = String.format("SELECT * FROM USERS AS i WHERE i.%s %s;", filed, condition);
            }
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("name");
                System.out.println(String.format("ID=%s, Name=%s",
                        rs.getInt("id"), name.substring(0, name.indexOf(32))));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}