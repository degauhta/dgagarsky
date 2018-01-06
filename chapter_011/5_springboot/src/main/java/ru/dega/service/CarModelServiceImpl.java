package ru.dega.service;

import org.springframework.stereotype.Service;
import ru.dega.domain.CarModel;
import ru.dega.reposirory.CarModelRepository;

import java.util.List;

/**
 * CarModelServiceImpl class.
 *
 * @author Denis
 * @since 05.01.2018
 */
@Service
public class CarModelServiceImpl implements CarModelService{
    /**
     * Car model repository.
     */
    private final CarModelRepository carModelRepository;

    /**
     * Instantiates a new Car model service.
     *
     * @param carModelRepository the car model repository
     */
    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    /**
     * Get all car models.
     *
     * @return list of car models
     */
    @Override
    public List<CarModel> getAll() {
        return (List<CarModel>) this.carModelRepository.findAll();
    }
}