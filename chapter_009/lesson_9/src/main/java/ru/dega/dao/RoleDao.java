package ru.dega.dao;

import ru.dega.models.Role;

import java.util.List;

/**
 * RoleDao class.
 *
 * @author Denis
 * @since 29.08.2017
 */
public interface RoleDao {
    /**
     * Get all roles.
     *
     * @return list of roles
     */
    List<Role> getAll();

    /**
     * Create new role.
     *
     * @param role role obj
     * @return id of new element
     */
    int create(Role role);

    /**
     * Get role by name.
     *
     * @param name name
     * @return role obj
     */
    Role getByName(String name);

    /**
     * Update role.
     *
     * @param role new role
     * @return true if update successfully
     */
    boolean update(Role role);

    /**
     * Delete role by name.
     *
     * @param name role name
     * @return true if delete successfully
     */
    boolean deleteByName(String name);
}