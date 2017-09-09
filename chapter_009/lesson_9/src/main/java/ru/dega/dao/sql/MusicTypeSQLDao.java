package ru.dega.dao.sql;

import org.apache.log4j.Logger;
import ru.dega.dao.DaoFactory;
import ru.dega.dao.MusicTypeDAO;
import ru.dega.models.MusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * MusicTypeSQLDao class.
 *
 * @author Denis
 * @since 31.08.2017
 */
@SuppressWarnings({"SqlDialectInspection", "Duplicates"})
public class MusicTypeSQLDao implements MusicTypeDAO {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MusicTypeSQLDao.class);

    /**
     * Get all music types.
     *
     * @return list of music types
     */
    @Override
    public List<MusicType> getAll() {
        LOGGER.info("Get all music types.");
        List<MusicType> list = new ArrayList<>();
        try (Connection connection = DaoFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM MUSIC_TYPE");) {
            while (resultSet.next()) {
                list.add(new MusicType(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get all music types", e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Create new music type.
     *
     * @param musicType musicType obj
     * @return id of new element
     */
    @Override
    public int createMusicType(MusicType musicType) {
        LOGGER.info(String.format("Create new music type %s", musicType.getName()));
        int id = -1;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO MUSIC_TYPE (NAME) "
                + "VALUES (?) RETURNING ID;";
        try (Connection connection = DaoFactory.getInstance().getConnection()) {
            statement = connection.prepareStatement(sql);
            statement.setString(1, musicType.getName());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot create music type %s", musicType.getName()), e);
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
     * Get music type by name.
     *
     * @param name name
     * @return music type obj
     */
    @Override
    public MusicType getByName(String name) {
        return null;
    }

    /**
     * Update music type.
     *
     * @param musicType new music type
     * @return true if update successfully
     */
    @Override
    public boolean update(MusicType musicType) {
        LOGGER.info(String.format("Update music type id=%s, new name is %s",
                musicType.getId(), musicType.getName()));
        boolean result = false;
        String sql = "UPDATE MUSIC_TYPE SET NAME = ? WHERE ID=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, musicType.getName());
            statement.setInt(2, musicType.getId());
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot update music type id=%s, with new name %s",
                    musicType.getId(), musicType.getName()), e);
        }
        return result;
    }

    /**
     * Delete music type.
     *
     * @param name music type name
     * @return true if delete successfully
     */
    @Override
    public boolean delete(String name) {
        LOGGER.info(String.format("Delete music type %s", name));
        boolean result = false;
        String sql = "DELETE FROM MUSIC_TYPE WHERE NAME=?;";
        try (Connection connection = DaoFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            if (statement.executeUpdate() != 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(String.format("Cannot delete music type %s", name), e);
        }
        return result;
    }
}