package ru.dega.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dega.models.CarModel;

/**
 * CarModelRepository class.
 *
 * @author Denis
 * @since 29.12.2017
 */
public interface CarModelRepository extends CrudRepository<CarModel, Integer> {
}