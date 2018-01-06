package ru.dega.service;

import ru.dega.domain.Advert;

import java.util.List;

/**
 * AdvertService class.
 *
 * @author Denis
 * @since 04.01.2018
 */
public interface AdvertService {
    /**
     * Get all adverts.
     *
     * @return list of adverts
     */
    List<Advert> getAll();

    /**
     * Add new advert.
     *
     * @param advert advert
     * @return advert with generated id
     */
    Advert add(Advert advert);
}