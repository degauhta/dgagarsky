package ru.dega.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dega.models.Advert;

/**
 * AdvertRepository class.
 *
 * @author Denis
 * @since 28.12.2017
 */
public interface AdvertRepository extends CrudRepository<Advert, Integer> {
}