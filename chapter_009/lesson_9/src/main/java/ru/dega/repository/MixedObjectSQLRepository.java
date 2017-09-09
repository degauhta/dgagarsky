package ru.dega.repository;

import org.apache.log4j.Logger;
import ru.dega.dao.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


/**
 * MixedObjectSQLRepository class.
 *
 * @author Denis
 * @since 04.09.2017
 */
public class MixedObjectSQLRepository implements Repository<String> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UserSQLRepository.class);

    @Override
    public List<String> query(Specification specification) {
        LOGGER.info("User query");
        String sql = specification.toSqlQuery();
        List<String> list = new ArrayList<>();
        try (Connection connection = DaoFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = resultSet.getMetaData().getColumnCount();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            String value = "";
            while (resultSet.next()) {
                stringBuilder.append("{");
                for (int i = 1; i <= columns; ++i) {
                    if (metaData.getColumnClassName(i).equals("java.sql.Array")) {
                        temp.append(resultSet.getObject(i));
                        value = temp.toString();
                        if (value.length() > 2) {
                            value = value.substring(1, value.length() - 1);
                        } else {
                            value = "";
                        }
                    } else {
                        temp.append(resultSet.getObject(i));
                        value = temp.toString();
                    }
                    stringBuilder.append(String.format("\"%s\":\"%s\"",
                            metaData.getColumnName(i), value));
                    if (i < columns) {
                        stringBuilder.append(", ");
                    }
                    temp = new StringBuilder();
                }
                stringBuilder.append("}");
                list.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot execute user query", e);
            e.printStackTrace();
        }
        return list;
    }
}