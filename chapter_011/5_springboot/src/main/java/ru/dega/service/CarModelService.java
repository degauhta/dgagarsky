package ru.dega.service;

import ru.dega.domain.CarModel;

import java.util.List;

/**
 * CarModelService class.
 *
 * @author Denis
 * @since 05.01.2018
 */
public interface CarModelService {
    /**
     * Get all car models.
     *
     * @return list of car models
     */
    List<CarModel> getAll();
}