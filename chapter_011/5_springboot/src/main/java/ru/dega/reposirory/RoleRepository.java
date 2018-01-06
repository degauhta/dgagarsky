package ru.dega.reposirory;

import org.springframework.data.repository.CrudRepository;
import ru.dega.domain.Role;

/**
 * RoleRepository class.
 *
 * @author Denis
 * @since 05.01.2018
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}