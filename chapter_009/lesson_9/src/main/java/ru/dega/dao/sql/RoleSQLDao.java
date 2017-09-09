package ru.dega.dao.sql;

import org.apache.log4j.Logger;
import ru.dega.dao.DaoFactory;
import ru.dega.dao.RoleDao;
import ru.dega.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * RoleSQLDao class.
 *
 * @author Denis
 * @since 29.08.2017
 */
@SuppressWarnings({"SqlDialectInspection", "Duplicates"})
public class RoleSQLDao implements RoleDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RoleSQLDao.class);

    /**
     * Get all roles.
     *
     * @return list of roles
     */
    @Override
    public List<Role> getAll() {
        LOGGER.info("Get all roles.");
        List<Role> list = new ArrayList<>();
        try (Connection connection = DaoFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ROLES");) {
            while (resultSet.next()) {
                list.add(new Role(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all roles", e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Create new role.
     *
     * @param role role obj
     * @return id of new element
     */
    @Override
    public int create(Role role) {
        LOGGER.info(String.format("Create new role %s", role.getName()));
        int id = -1;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO ROLES (NAME) "
                + "VALUES (?) RETURNING ID;";
        try (Connection connection = DaoFactory.getInstance().getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, role.getName());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot create new role %s", role.getName()), e);
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
     * Get role by name.
     *
     * @param name name
     * @return role obj
     */
    @Override
    public Role getByName(String name) {
        return null;
    }

    /**
     * Update role.
     *
     * @param role new role
     * @return true if update successfully
     */
    @Override
    public boolean update(Role role) {
        LOGGER.info(String.format("Update role id=%s, new name is %s", role.getId(), role.getName()));
        boolean result = false;
        String sql = "UPDATE ROLES SET NAME = ? WHERE ID=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot update role id=%s, with new name %s",
                    role.getId(), role.getName()), e);
        }
        return result;
    }

    /**
     * Delete role by name.
     *
     * @param name role name
     * @return true if delete successfully
     */
    @Override
    public boolean deleteByName(String name) {
        LOGGER.info(String.format("Delete role %s", name));
        boolean result = false;
        String sql = "DELETE FROM ROLES WHERE NAME=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot delete role %s", name), e);
        }
        return result;
    }
}