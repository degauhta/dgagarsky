package ru.dega;

/**
 * ImportUser class.
 *
 * @author Denis
 * @since 03.10.2017
 */
class ImportUser {
    /**
     * Storage.
     */
    private final Storage storage;

    /**
     * Default constructor.
     *
     * @param storage the storage
     */
    ImportUser(final Storage storage) {
        this.storage = storage;
    }

    /**
     * Add user.
     *
     * @param user the user
     * @return id of added user
     */
    int add(User user) {
        return this.storage.add(user);
    }

    /**
     * Gets user.
     *
     * @param id user id
     * @return the users
     */
    User getUser(int id) {
        return this.storage.getUser(id);
    }
}