package ru.dega.reposirory;

import org.springframework.data.repository.CrudRepository;
import ru.dega.domain.CarModel;

/**
 * CarModelRepository class.
 *
 * @author Denis
 * @since 29.12.2017
 */
public interface CarModelRepository extends CrudRepository<CarModel, Integer> {
}