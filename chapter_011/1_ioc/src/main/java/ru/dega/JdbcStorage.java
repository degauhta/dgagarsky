package ru.dega;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JdbcStorage class.
 *
 * @author Denis
 * @since 06.10.2017
 */
public class JdbcStorage implements Storage {
    /**
     * JdbcTemplate.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Instantiates a new Jdbc storage.
     *
     * @param jdbcTemplate the jdbc template
     */
    public JdbcStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add user to storage.
     *
     * @param user user
     * @return id of added user
     */
    @Override
    public int add(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection
                        .prepareStatement("INSERT INTO USERS(NAME) VALUES (?);",
                                Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                return ps;
            }
        }, holder);
        return (int) holder.getKeys().get("id");
    }

    /**
     * Gets user.
     *
     * @param id user id
     * @return the users
     */
    @Override
    public User getUser(int id) {
        String sql = "SELECT NAME FROM USERS WHERE ID = ?";
        String name = (String) jdbcTemplate.queryForObject(
                sql, new Object[]{id}, String.class);
        return new User(name);
    }
}