package ru.dega.dao;

import ru.dega.models.Advertisement;

import java.util.List;

/**
 * AdvertisementDao class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public interface AdvertisementDao {
    /**
     * Create new advert.
     *
     * @param advertisement advertisement
     */
    void createAdvertisement(Advertisement advertisement);

    /**
     * Get all advert.
     *
     * @return list of advert
     */
    List<Advertisement> getAllAdvertisement();

    /**
     * Get all not sold advert.
     *
     * @return list of advert
     */
    List<Advertisement> getAllNotSoldAdvertisement();

    /**
     * Set sold status.
     *
     * @param id advert id
     * @return true if status set successfully
     */
    boolean updateSoldStatus(int id);

    /**
     * Get filtered avert.
     *
     * @param filters filters
     * @return list of advert
     */
    List<Advertisement> getFilteredAdvertisement(String[] filters);
}