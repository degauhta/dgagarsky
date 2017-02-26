package ru.job4j;

/**
 * UserStore class.
 *
 * @param <T> type
 * @author Denis
 * @since 26.02.2017
 */
public class UserStore<T extends Base> extends AbstractStore<T> {
    /**
     * Main constructor.
     *
     * @param size data size
     */
    public UserStore(int size) {
        super(size);
    }
}
