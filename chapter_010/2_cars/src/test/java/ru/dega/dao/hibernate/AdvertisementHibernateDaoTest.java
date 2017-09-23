package ru.dega.dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import ru.dega.dao.AdvertisementDao;
import ru.dega.models.Advertisement;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * AdvertisementHibernateDaoTest class.
 *
 * @author Denis
 * @since 23.09.2017
 */
public class AdvertisementHibernateDaoTest {
    /**
     * Advertisement instance.
     */
    private Advertisement advertisement;

    /**
     * AdvertisementDao instance.
     */
    private AdvertisementDao advertisementDao;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        advertisement = new Advertisement();
        advertisementDao = new AdvertisementHibernateDao();
    }

    /**
     * Test that name of advertisement added in db, is same of instance.
     */
    @Test
    public void whenCreateAdvertisementInDBThenItCreated() {
        advertisement.setDescription("Description");
        advertisementDao.createAdvertisement(advertisement);
        List<Advertisement> advertisementList = advertisementDao.getAllAdvertisement();
        assertThat(advertisement.getDescription(), is(advertisementList.get(0).getDescription()));
    }

    /**
     * Test that sold status updated.
     */
    @Test
    public void whenUpdateSoldStatusThenReturnTrue() {
        advertisementDao.createAdvertisement(advertisement);
        advertisementDao.updateSoldStatus(1);
        List<Advertisement> advertisementList = advertisementDao.getAllAdvertisement();
        assertThat(true, is(advertisementList.get(0).isSold()));
    }

    /**
     * Test getting filtered adverts.
     */
    @Test
    public void whenGetFilteredAdvertThenReturnOnlyFits() {
        Advertisement advert = new Advertisement();
        advert.setDescription("criteria");
        advertisementDao.createAdvertisement(advertisement);
        advertisementDao.createAdvertisement(advert);
        String[] filters = {"crit", "", "", "", "", "", "", ""};
        List<Advertisement> advertisementList = advertisementDao.getFilteredAdvertisement(filters);
        assertThat(1, is(advertisementList.size()));
        assertThat(advert.getDescription(), is(advertisementList.get(0).getDescription()));
    }
}