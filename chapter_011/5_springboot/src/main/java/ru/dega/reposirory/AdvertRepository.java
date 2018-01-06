package ru.dega.reposirory;

import org.springframework.data.repository.CrudRepository;
import ru.dega.domain.Advert;

/**
 * AdvertRepository class.
 *
 * @author Denis
 * @since 28.12.2017
 */
public interface AdvertRepository extends CrudRepository<Advert, Integer> {
}