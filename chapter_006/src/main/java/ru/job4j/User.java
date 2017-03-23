package ru.job4j;

/**
 * User class.
 *
 * @author Denis
 * @since 23.03.2017
 */
class User {
    /**
     * Age.
     */
    private String name;

    /**
     * Main constructor.
     *
     * @param name name
     */
    User(String name) {
        this.name = name;
    }

    /**
     * Finalize.
     *
     * @throws Throwable error
     */
    @Override
    protected void finalize() throws Throwable {
        memory();
        super.finalize();
        System.out.print(String.format("invoke finalize %s", this));
    }

    /**
     * String representation.
     *
     * @return string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Memory.
     */
    private void memory() {
        Runtime runtime = Runtime.getRuntime();
        System.out.print(String.format(" ___ Total: %s", runtime.totalMemory()));
        System.out.print(String.format(" / Free: %s", runtime.freeMemory()));
        System.out.println(String.format(" / Used: %s",
                (runtime.totalMemory() - runtime.freeMemory())));
    }

    /**
     * Main.
     *
     * @param args args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 1_000; i++) {
            new User(String.format("test%s", i));
        }
    }
}
