package ru.job4j;

/**
 * RoleStore class.
 *
 * @param <T> type
 * @author Denis
 * @since 26.02.2017
 */
public class RoleStore<T extends Base> extends AbstractStore<T> {
    /**
     * Main constructor.
     *
     * @param size data size
     */
    public RoleStore(int size) {
        super(size);
    }
}

