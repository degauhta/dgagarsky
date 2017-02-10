package ru.job4j;

/**
 * BaseActionCalc class.
 *
 * @author Denis
 * @since 06.01.2017
 */
public abstract class BaseActionCalc implements UserActionCalc {
    /**
     * Name (description) of the action.
     */
    private String name;

    /**
     * Constructor.
     * @param name of the action.
     */
    public BaseActionCalc(String name) {
        this.name = name;
    }

    /**
     * Menu unique key.
     *
     * @return key.
     */
    public abstract int key();

    /**
     * Execute action of tracker.
     *
     * @param input   input.
     * @param calculator calculator.
     */
    public abstract void execute(InputCalc input, Calculator calculator);

    /**
    * Menu desc.
    *
    * @return description.
    */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}
