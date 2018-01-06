package ru.dega.reposirory;

import org.springframework.data.repository.CrudRepository;
import ru.dega.domain.CarBrand;

/**
 * CarBrandRepository class.
 *
 * @author Denis
 * @since 28.12.2017
 */
public interface CarBrandRepository extends CrudRepository<CarBrand, Integer> {
}