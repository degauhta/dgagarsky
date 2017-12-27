package ru.dega.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.dega.models.Advert;
import ru.dega.models.CarBrand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AdvertDAO class.
 *
 * @author Denis
 * @since 26.12.2017
 */
@Component
public class AdvertDAO {
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
    public List<Advert> list() {
        List<Advert> arrayList = new ArrayList<>();
        String sql = "SELECT a.ADVERTISEMENT_ID, a.DESCRIPTION, c.CAR_BRAND_ID, c.NAME FROM ADVERTISEMENTS a "
                + "INNER JOIN CAR_BRANDS c ON a.CAR_BRAND_ID = c.CAR_BRAND_ID";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            CarBrand carBrand = new CarBrand();
            carBrand.setId((Integer) row.get("car_brand_id"));
            carBrand.setName((String) row.get("name"));
            Advert advert = new Advert();
            advert.setId((Integer) row.get("advertisement_id"));
            advert.setDescription((String) row.get("description"));
            advert.setCarBrand(carBrand);
            arrayList.add(advert);
        }
        List<Advert> test =
                jdbcTemplate.query(
                        "SELECT ad.ADVERTISEMENT_ID as id, ad.DESCRIPTION as description, ad.CAR_BRAND_ID as carBrand FROM ADVERTISEMENTS ad",
                        new BeanPropertyRowMapper(Advert.class));
        return arrayList;
    }

    /**
     * Insert.
     *
     * @param description the description
     * @param carBrandId  the car brand id
     */
    public void insert(String description, int carBrandId) {
        jdbcTemplate.update(
                "INSERT INTO ADVERTISEMENTS (DESCRIPTION, CAR_BRAND_ID) VALUES (?, ?)",
                description, carBrandId);
    }
}