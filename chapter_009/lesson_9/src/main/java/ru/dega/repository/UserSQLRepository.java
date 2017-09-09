package ru.dega.repository;

import org.apache.log4j.Logger;
import ru.dega.models.User;

import java.util.List;

/**
 * UserSQLRepository class.
 *
 * @author Denis
 * @since 04.09.2017
 */
public class UserSQLRepository implements Repository<User> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UserSQLRepository.class);

    @Override
    public List<User> query(Specification specification) {
        return null;
    }
}
