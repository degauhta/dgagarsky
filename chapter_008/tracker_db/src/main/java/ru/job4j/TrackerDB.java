package ru.job4j;

import ru.job4j.models.Item;
import ru.job4j.start.Tracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TrackerDB class.
 *
 * @author Denis
 * @since 01.07.2017
 */
class TrackerDB implements Tracker {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Default constructor.
     */
    TrackerDB() {
        this.connection = new ConnectDB().getConnection();
    }

    /**
     * Add request to storage.
     *
     * @param item request.
     * @return ref to request.
     */
    public Item add(Item item) {
        Statement statement;
        String sql;
        try {
            statement = connection.createStatement();
            sql = String.format("INSERT INTO TASKS (NAME, DESCRIPTION) " +
                            "VALUES ('%s','%s') RETURNING ID;", item.getName(), item.getDescription());
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            item.setId(String.valueOf(rs.getInt(1)));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Find request. Searching in fields name and description.
     *
     * @param sub substring to find.
     * @return finding request.
     */
    public Item[] filterRequest(String sub) {
        Item[] result = null;
        int length = 0;
        int i = 0;
        String name;
        String description;
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("SELECT COUNT(*) FROM TASKS "
                    + "WHERE NAME LIKE '%%%s%%' OR DESCRIPTION LIKE '%%%s%%'", sub, sub);
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    length = rs.getInt(1);
                }
            }
            sql = String.format("SELECT * FROM TASKS "
                    + "WHERE NAME LIKE '%%%s%%' OR DESCRIPTION LIKE '%%%s%%'", sub, sub);
            try (ResultSet rs = statement.executeQuery(sql)) {
                result = new Item[length];
                while (rs.next()) {
                    name = rs.getString("name");
                    description = rs.getString("description");
                    result[i++] = new Item(name.replaceAll("\\s+$", ""), description.replaceAll("\\s+$", ""), 1L);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Print all requests.
     *
     * @return all requests.
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
    public Item[] getAll() {
        Item[] result = null;
        int length = 0;
        int i = 0;
        String name;
        String description;
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM TASKS")) {
                while (rs.next()) {
                    length = rs.getInt(1);
                }
            }
            try (ResultSet rs = statement.executeQuery("SELECT * FROM TASKS ORDER BY id ASC")) {
                result = new Item[length];
                while (rs.next()) {
                    name = rs.getString("name");
                    description = rs.getString("description");
                    result[i] = new Item(name.replaceAll("\\s+$", ""), description.replaceAll("\\s+$", ""), 1L);
                    result[i++].setId(String.valueOf(rs.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Editing request.
     *
     * @param item request to edit.
     * @return edited request or null if request not found in tracker.
     */
    public Item editRequest(Item item) {
        Item result = null;
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("UPDATE TASKS SET NAME = '%s', "
                    + "DESCRIPTION = '%s' WHERE ID=%s;", item.getName(), item.getDescription(), item.getId());
            if (statement.executeUpdate(sql) != 0) {
                result = item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param removeItem request that need to delete.
     * @return true if remove successful.
     */
    public boolean removeRequest(Item removeItem) {
        boolean result = false;
        try (Statement statement = this.connection.createStatement()) {
            String sql = String.format("DELETE from tasks where ID = %s;", removeItem.getId());
            if (statement.executeUpdate(sql) != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}