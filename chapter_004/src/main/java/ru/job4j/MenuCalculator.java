package ru.job4j;

/**
 * MenuCalculator class.
 *
 * @author Denis
 * @since 07.02.2017
 */
public class MenuCalculator {
    /**
     * Input.
     */
    private InputCalc input;

    /**
     * Calculator.
     */
    private Calculator calculator;

    /**
     * User actions.
     */
    private UserActionCalc[] userActions;

    /**
     * Constructor.
     * @param input input.
     * @param calculator calculator.
     * @param menuSize menu size.
     */
    public MenuCalculator(InputCalc input, Calculator calculator, int menuSize) {
        this.input = input;
        this.calculator = calculator;
        this.userActions = new UserActionCalc[menuSize];
    }

    /**
     * Fill userActions.
     * @return array of possible user action.
     */
    public int[] fillAction() {
        this.userActions[0] = new Addition("Addition.");
        this.userActions[1] = new Subtraction("Subtraction.");
        this.userActions[2] = new Multiplication("Multiplication.");
        this.userActions[3] = new Division("Division.");
        int[] possibleAction = new int[userActions.length];
        for (int i = 0; i < this.userActions.length; i++) {
            possibleAction[i] = this.userActions[i].key();
        }
        return possibleAction;
    }

    /**
     * Run selected action.
     * @param key of action.
     */
    public void select(int key) {
        this.userActions[key].execute(input, calculator);
    }

    /**
     * Show all actions in console.
     */
    public void showMenu() {
        for (UserActionCalc userAction : this.userActions) {
            showAction(userAction);
        }
    }

    /**
     * Show action in console.
     *
     * @param userAction userAction.
     */
    public void showAction(UserActionCalc userAction) {
        System.out.println(userAction.info());
    }

    /**
     * Addition of two numbers.
     */
    private class Addition extends BaseActionCalc {
        /**
         * Constructor.
         * @param name of action.
         */
        Addition(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 0;
        }

        /**
         * Addition.
         *
         * @param input   input.
         * @param calculator calculator.
         */
        public void execute(InputCalc input, Calculator calculator) {
            double a = input.askDouble("Enter first number");
            double b = input.askDouble("Enter second number");
            calculator.add(a, b);
            System.out.println(calculator.getResult());
        }
    }

    /**
     * Subtraction of two numbers.
     */
    private class Subtraction extends BaseActionCalc {
        /**
         * Constructor.
         * @param name of action.
         */
        Subtraction(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 1;
        }

        /**
         * Subtraction.
         *
         * @param input   input.
         * @param calculator calculator.
         */
        public void execute(InputCalc input, Calculator calculator) {
            double a = input.askDouble("Enter first number");
            double b = input.askDouble("Enter second number");
            calculator.substruct(a, b);
            System.out.println(calculator.getResult());
        }
    }

    /**
     * Multiplication.
     */
    private class Multiplication extends BaseActionCalc {
        /**
         * Constructor.
         * @param name of action.
         */
        Multiplication(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 2;
        }

        /**
         * Multiplication.
         *
         * @param input   input.
         * @param calculator calculator.
         */
        public void execute(InputCalc input, Calculator calculator) {
            double a = input.askDouble("Enter first number");
            double b = input.askDouble("Enter second number");
            calculator.multiple(a, b);
            System.out.println(calculator.getResult());
        }
    }

    /***
     * Division.
     */
    private class Division extends BaseActionCalc {
        /**
         * Constructor.
         * @param name of action.
         */
        Division(String name) {
            super(name);
        }

        /**
         * Menu unique key.
         *
         * @return key.
         */
        public int key() {
            return 3;
        }

        /**
         * Division.
         *
         * @param input   input.
         * @param calculator calculator.
         */
        public void execute(InputCalc input, Calculator calculator) {
            double a = input.askDouble("Enter first number");
            double b = input.askDouble("Enter second number");
            calculator.div(a, b);
            System.out.println(calculator.getResult());
        }
    }

}
