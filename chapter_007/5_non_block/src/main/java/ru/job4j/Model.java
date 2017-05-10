package ru.job4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Model class.
 *
 * @author Denis
 * @since 06.05.2017
 */
class Model {
    /**
     * Name.
     */
    private String name;

    /**
     * Version.
     */
    private AtomicInteger version;

    /**
     * Default constructor.
     *
     * @param name model name
     */
    Model(String name) {
        this.name = name;
        this.version = new AtomicInteger(0);
    }

    /**
     * Get version.
     *
     * @return version
     */
    int getVersion() {
        return this.version.get();
    }

    /**
     * Get name.
     *
     * @return name
     */
    String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Atomically sets the value to the given updated value
     * if the current value {@code ==} the expected value.
     *
     * @param expected the expected value
     * @return {@code true} if successful. False return indicates that
     */
    boolean compareAndSetVersion(int expected) {
        return this.version.compareAndSet(expected, expected + 1);
    }
}
