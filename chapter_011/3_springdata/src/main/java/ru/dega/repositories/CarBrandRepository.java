package ru.dega.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dega.models.CarBrand;

/**
 * CarBrandRepository class.
 *
 * @author Denis
 * @since 28.12.2017
 */
public interface CarBrandRepository extends CrudRepository<CarBrand, Integer> {
}