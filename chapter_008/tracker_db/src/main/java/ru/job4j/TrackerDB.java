package ru.job4j;

import ru.job4j.models.Item;
import ru.job4j.start.Tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * TrackerDB class.
 *
 * @author Denis
 * @since 01.07.2017
 */
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
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
        String sql = "INSERT INTO TASKS (NAME, DESCRIPTION) VALUES (?, ?) RETURNING ID;";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                item.setId(String.valueOf(rs.getInt(1)));
            }
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
        String name;
        String description;
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM TASKS WHERE NAME LIKE ? OR DESCRIPTION LIKE ?";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            preparedStatement.setString(1, "%" + sub + "%");
            preparedStatement.setString(2, "%" + sub + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                    description = rs.getString("description");
                    list.add(new Item(name, description, 1L));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Item[list.size()]);
    }

    /**
     * Print all requests.
     *
     * @return all requests.
     */
    public Item[] getAll() {
        String name;
        String description;
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM TASKS ORDER BY id ASC";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                    description = rs.getString("description");
                    Item item = new Item(name, description, 1L);
                    item.setId(String.valueOf(rs.getInt("id")));
                    list.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Item[list.size()]);
    }

    /**
     * Editing request.
     *
     * @param item request to edit.
     * @return edited request or null if request not found in tracker.
     */
    public Item editRequest(Item item) {
        Item result = null;
        String sql = "UPDATE TASKS SET NAME = ?, DESCRIPTION = ? WHERE ID=?;";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setInt(3, Integer.valueOf(item.getId()));
            if (preparedStatement.executeUpdate() != 0) {
                result = item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Remove request.
     *
     * @param removeItem request that need to delete.
     * @return true if remove successful.
     */
    public boolean removeRequest(Item removeItem) {
        boolean result = false;
        String sql = "DELETE from tasks where ID = ?;";
        try (PreparedStatement preparedStatement = preparedStatement(sql)) {
            preparedStatement.setInt(1, Integer.valueOf(removeItem.getId()));
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