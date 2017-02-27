package ru.job4j;

/**
 * UserStore class.
 *
 * @author Denis
 * @since 26.02.2017
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Main constructor.
     *
     * @param size data size
     */
    public UserStore(int size) {
        super(size);
    }
}
