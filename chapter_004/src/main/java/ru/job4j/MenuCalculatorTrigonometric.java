package ru.job4j;

import java.util.Arrays;

/**
 * MenuCalculatorTrigonometric class.
 *
 * @author Denis
 * @since 11.02.2017
 */
public class MenuCalculatorTrigonometric extends MenuCalculator {
    /**
     * Input.
     */
    private InputCalc input;

    /**
     * Calculator.
     */
    private CalculatorTrigonometric calculator;

    /**
     * User actions.
     */
    private UserActionCalcTrig[] userActions;

    /**
     * Menu size in MenuCalculator class.
     */
    private final int menuSize;

    /**
     * Default constructor.
     *
     * @param input input.
     * @param calculator calculator.
     * @param menuSize menuSize.
     */
    public MenuCalculatorTrigonometric(InputCalc input, CalculatorTrigonometric calculator, int menuSize) {
        super(input, calculator, 4);
        this.menuSize = menuSize;
        this.userActions = new UserActionCalcTrig[menuSize];
        this.input = input;
        this.calculator = calculator;
    }

    /**
     * Fill userActions.
     *
     * @return array of possible user action.
     */
    @Override
    public int[] fillAction() {
        int[] possibleActionArithmetic = super.fillAction();
        userActions[4] = new Sines("Sines.");
        userActions[5] = new Cosines("Cosines.");
        int[] possibleAction = Arrays.copyOf(possibleActionArithmetic, menuSize);
        for (int i = 4; i < this.userActions.length; i++) {
            possibleAction[i] = this.userActions[i].key();
        }
        return possibleAction;
    }

    /**
     * Show all actions in console.
     */
    @Override
    public void showMenu() {
        super.showMenu();
        for (int i = 4; i < this.userActions.length; i++) {
            showAction(userActions[i]);
        }
    }

    /**
     * Show action in console.
     *
     * @param userAction userAction.
     */
    @Override
    public void showAction(UserActionCalc userAction) {
        System.out.println(userAction.info());
    }

    /**
     * Run selected action.
     *
     * @param key of action.
     */
    @Override
    public void select(int key) {
        if (key < 4) {
            super.select(key);
        } else {
            this.userActions[key].execute(input, calculator);
        }
    }

    /***
     * Sines.
     */
    private class Sines extends BaseActionCalcTrigonometric {
        /**
         * Constructor.
         * @param name of action.
         */
        Sines(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 4;
        }

        /**
         * Sines.
         *
         * @param input   input.
         * @param calculator calculator.
         */
        public void execute(InputCalc input, Calculator calculator) {

        }

        /**
         * Sines action.
         *
         * @param input      input.
         * @param calculator calculator.
         */
        @Override
        public void execute(InputCalc input, CalculatorTrigonometric calculator) {
            double a = input.askDouble("Enter degree");
            calculator.sin(a);
            System.out.println(calculator.getResult());
        }
    }

    /***
     * Cosines.
     */
    private class Cosines extends BaseActionCalcTrigonometric {
        /**
         * Constructor.
         * @param name of action.
         */
        Cosines(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 5;
        }

        /**
         * Sines.
         *
         * @param input   input.
         * @param calculator calculator.
         */
        public void execute(InputCalc input, Calculator calculator) {

        }

        /**
         * Sines action.
         *
         * @param input      input.
         * @param calculator calculator.
         */
        @Override
        public void execute(InputCalc input, CalculatorTrigonometric calculator) {
            double a = input.askDouble("Enter degree");
            calculator.cos(a);
            System.out.println(calculator.getResult());
        }
    }


}
