package ru.dega.dao.sql;

import org.apache.log4j.Logger;
import ru.dega.dao.DaoFactory;
import ru.dega.dao.UserDao;
import ru.dega.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * UserSQLDao class.
 *
 * @author Denis
 * @since 30.08.2017
 */
@SuppressWarnings({"SqlResolve", "Duplicates"})
public class UserSQLDao implements UserDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UserSQLDao.class);

    /**
     * Get all users.
     *
     * @return list of users
     */
    @Override
    public List<User> getAll() {
        LOGGER.info("Get all users");
        List<User> list = new ArrayList<>();
        String sql = "SELECT u.id AS userid, u.login AS userlogin, r.name AS rolename, r.id AS roleid "
                + "FROM USERS AS u LEFT JOIN roles AS r ON u.role_id=r.id";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new User(resultSet.getInt("userid"),
                        resultSet.getString("userlogin"),
                        resultSet.getInt("roleid")));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all roles", e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Create new user.
     *
     * @param user    user obj
     * @param musicID music types
     * @return id of new element
     */
    @Override
    public int createUser(User user, String[] musicID) {
        LOGGER.info(String.format("Create new user %s", user.getLogin()));
        int id = -1;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO USERS (LOGIN, ROLE_ID) "
                + "VALUES (?, ?) RETURNING ID;";
        try (Connection connection = DaoFactory.getInstance().getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setInt(2, user.getRoleId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("ID");
                sql = "INSERT INTO MUSICTYPE_USERS (USER_ID, MUSICTYPE_ID) "
                        + "VALUES (?, ?);";
                statement = connection.prepareStatement(sql);
                if (musicID != null) {
                    for (String mID : musicID) {
                        statement.setInt(1, id);
                        statement.setInt(2, Integer.valueOf(mID));
                        statement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cant create user %s", user.getLogin()), e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    /**
     * Get user by name.
     *
     * @param name name
     * @return user obj
     */
    @Override
    public User getByName(String name) {
        LOGGER.info(String.format("Get user by name %s", name));
        User result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM USERS WHERE LOGIN=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new User(resultSet.getInt("ID"),
                        resultSet.getString("LOGIN"),
                        resultSet.getInt("ROLE_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot get user by name %s", name), e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Update user.
     *
     * @param user     user
     * @param musicID  music types
     * @param sameUser true if login not change
     * @return true if update successfully
     */
    @Override
    public boolean update(User user, String[] musicID, boolean sameUser) {
        LOGGER.info(String.format("Update user %s", user.getLogin()));
        boolean result = false;
        PreparedStatement statement = null;
        String sql = null;
        if (sameUser) {
            sql = "UPDATE USERS SET ROLE_ID = ? WHERE ID = ?;";
        } else {
            sql = "UPDATE USERS SET LOGIN = ?, ROLE_ID = ? WHERE ID = ?;";
        }
        try (Connection connection = DaoFactory.getInstance().getConnection()) {
            statement = connection.prepareStatement(sql);
            if (sameUser) {
                statement.setInt(1, user.getRoleId());
                statement.setInt(2, user.getId());
            } else {
                statement.setString(1, user.getLogin());
                statement.setInt(2, user.getRoleId());
                statement.setInt(3, user.getId());
            }
            result = statement.executeUpdate() != 0;
            if (result) {
                sql = "DELETE FROM MUSICTYPE_USERS WHERE USER_ID = ?;";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, user.getId());
                statement.executeUpdate();
                sql = "INSERT INTO MUSICTYPE_USERS (USER_ID, MUSICTYPE_ID) "
                        + "VALUES (?, ?);";
                statement = connection.prepareStatement(sql);
                if (musicID != null) {
                    for (String mID : musicID) {
                        statement.setInt(1, user.getId());
                        statement.setInt(2, Integer.valueOf(mID));
                        statement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cant update user %s", user.getLogin()), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Delete user by id.
     *
     * @param id user id
     * @return true if delete successfully
     */
    @Override
    public boolean deleteByID(int id) {
        LOGGER.info(String.format("Delete user %s", id));
        boolean result = false;
        String sql = "DELETE FROM USERS WHERE id=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot delete user by id %s", id), e);
        }
        return result;
    }
}