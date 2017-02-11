package ru.job4j;

/**
 * UserActionCalcTrig class.
 *
 * @author Denis
 * @since 11.02.2017
 */
public interface UserActionCalcTrig extends UserActionCalc {
    /**
     * Execute operation.
     * @param input input.
     * @param calculator tracker.
     */
    void execute(InputCalc input, CalculatorTrigonometric calculator);
}
