package ru.dega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dega.domain.CarBrand;
import ru.dega.reposirory.CarBrandRepository;

import java.util.List;

/**
 * CarBrandServiceImpl class.
 *
 * @author Denis
 * @since 05.01.2018
 */
@Service
public class CarBrandServiceImpl implements CarBrandService {
    /**
     * Car brand repository.
     */
    private final CarBrandRepository carBrandRepository;

    /**
     * Instantiates a new Car brand service.
     *
     * @param carBrandRepository the car brand repository
     */
    @Autowired
    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    /**
     * Get all car brands.
     *
     * @return list of car brands
     */
    @Override
    public List<CarBrand> getAll() {
        return (List<CarBrand>) this.carBrandRepository.findAll();
    }
}