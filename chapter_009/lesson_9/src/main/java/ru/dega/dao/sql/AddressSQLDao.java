package ru.dega.dao.sql;

import org.apache.log4j.Logger;
import ru.dega.dao.AddressDao;
import ru.dega.dao.DaoFactory;
import ru.dega.models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * AddressSQLDao class.
 *
 * @author Denis
 * @since 01.09.2017
 */
@SuppressWarnings({"SqlDialectInspection", "Duplicates"})
public class AddressSQLDao implements AddressDao {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AddressSQLDao.class);

    /**
     * Create new addresses.
     *
     * @param address address obj
     * @return id of new element
     */
    @Override
    public int create(Address address) {
        LOGGER.info(String.format("Create new address %s, for userID %s",
                address.getRepresentation(), address.getUserId()));
        int id = -1;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO ADDRESS (REPRESENTATION, USER_ID) "
                + "VALUES (?, ?) RETURNING ID;";
        try (Connection connection = DaoFactory.getInstance().getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, address.getRepresentation());
            statement.setInt(2, address.getUserId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot create new address %s, for userID %s",
                    address.getRepresentation(), address.getUserId()), e);
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
     * Get address by user id.
     *
     * @param userId user id
     * @return address obj
     */
    @Override
    public Address getByUserId(int userId) {
        LOGGER.info(String.format("Get address by userID %s", userId));
        Address result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ADDRESS WHERE USER_ID=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();) {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Address(resultSet.getInt("ID"),
                        resultSet.getString("REPRESENTATION"),
                        resultSet.getInt("USER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot get address by userID %s", userId), e);
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
     * Update address.
     *
     * @param address address
     * @return true if update successfully
     */
    @Override
    public boolean update(Address address) {
        LOGGER.info(String.format("Update address from userID %s, with new representation is %s",
                address.getUserId(), address.getRepresentation()));
        boolean result = false;
        String sql = "UPDATE ADDRESS SET REPRESENTATION = ? WHERE USER_ID = ?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, address.getRepresentation());
            statement.setInt(2, address.getUserId());
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot update address from userID %s, "
                            + "with new representation is %s", address.getUserId(),
                    address.getRepresentation()));
        }
        return result;
    }

    /**
     * Delete address.
     *
     * @param address address
     * @return true if delete successfully
     */
    @Override
    public boolean delete(Address address) {
        return false;
    }
}