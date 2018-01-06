package ru.dega.service;

import ru.dega.domain.CarBrand;

import java.util.List;

/**
 * CarBrandService class.
 *
 * @author Denis
 * @since 05.01.2018
 */
public interface CarBrandService {
    /**
     * Get all car brands.
     *
     * @return list of car brands
     */
    List<CarBrand> getAll();
}