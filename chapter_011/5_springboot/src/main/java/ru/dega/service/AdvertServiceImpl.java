package ru.dega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dega.domain.Advert;
import ru.dega.reposirory.AdvertRepository;

import java.util.List;

/**
 * AdvertServiceImpl class.
 *
 * @author Denis
 * @since 04.01.2018
 */
@Service
public class AdvertServiceImpl implements AdvertService {
    /**
     * Advert repository.
     */
    private final AdvertRepository advertRepository;

    /**
     * Instantiates a new Advert service.
     *
     * @param advertRepository the advert repository
     */
    @Autowired
    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    /**
     * Get all adverts.
     *
     * @return list of adverts
     */
    @Override
    public List<Advert> getAll() {
        return (List<Advert>) advertRepository.findAll();
    }

    /**
     * Add new advert.
     *
     * @param advert advert
     * @return advert with generated id
     */
    @Override
    public Advert add(Advert advert) {
        return advertRepository.save(advert);
    }
}