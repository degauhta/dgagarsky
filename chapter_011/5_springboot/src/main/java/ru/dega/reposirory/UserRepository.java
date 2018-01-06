package ru.dega.reposirory;

import org.springframework.data.repository.CrudRepository;
import ru.dega.domain.User;

/**
 * UserRepository class.
 *
 * @author Denis
 * @since 05.01.2018
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}