package ru.dega.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dega.models.CarBrand;

import java.util.List;

/**
 * UserDAO class.
 *
 * @author Denis
 * @since 26.12.2017
 */
@Component
public class UserDAO {
    /**
     * JDBC.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * List list.
     *
     * @return the list
     */
    public List<CarBrand> list() {
        return jdbcTemplate.query("SELECT C.CAR_BRAND_ID AS id, C.NAME AS name FROM CAR_BRANDS AS C",
                new BeanPropertyRowMapper(CarBrand.class));
    }
}