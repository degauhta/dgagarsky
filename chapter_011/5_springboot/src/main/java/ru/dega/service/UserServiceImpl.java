package ru.dega.service;

import org.springframework.stereotype.Service;
import ru.dega.domain.User;
import ru.dega.reposirory.UserRepository;

import java.util.List;

/**
 * UserServiceImpl class.
 *
 * @author Denis
 * @since 05.01.2018
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * User repository.
     */
    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users.
     *
     * @return list of users
     */
    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Find user by id.
     *
     * @param id id
     * @return user
     */
    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }
}