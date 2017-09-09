package ru.dega.dao;

import ru.dega.models.Address;

/**
 * AddressDao class.
 *
 * @author Denis
 * @since 01.09.2017
 */
public interface AddressDao {
    /**
     * Create new addresses.
     *
     * @param address address obj
     * @return id of new element
     */
    int create(Address address);

    /**
     * Get address by user id.
     *
     * @param userId user id
     * @return address obj
     */
    Address getByUserId(int userId);

    /**
     * Update address.
     *
     * @param address address
     * @return true if update successfully
     */
    boolean update(Address address);

    /**
     * Delete address.
     *
     * @param address address
     * @return true if delete successfully
     */
    boolean delete(Address address);
}