package ru.job4j;

/**
 * BaseActionCalcTrigonometric class.
 *
 * @author Denis
 * @since 11.02.2017
 */
public abstract class BaseActionCalcTrigonometric extends BaseActionCalc implements UserActionCalcTrig {
    /**
     * Constructor.
     *
     * @param name of the action.
     */
    public BaseActionCalcTrigonometric(String name) {
        super(name);
    }

    /**
     * Execute action of tracker.
     *
     * @param input      input.
     * @param calculator calculator.
     */
    public abstract void execute(InputCalc input, CalculatorTrigonometric calculator);

}
