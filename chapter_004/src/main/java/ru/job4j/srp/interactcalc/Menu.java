package ru.job4j.srp.interactcalc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class Menu - console menu for calculator.
 * @author aeremeev
 * @since 06.01.2018
 * @version 1
 */
public class Menu {
    /**
     * Input interface.
     */
    private Input input;
    /**
     * Calculator module.
     */
    private Calculator calculator;
    /**
     * Result of operation.
     */
    private double result;
    /**
     * Second argument in operation.
     */
    private double secondArg;
    /**
     * Use result value flag.
     */
    private boolean useResult;
    /**
     * Repeat last operation flag.
     */
    private boolean repeat;
    /**
     * Saved action string.
     */
    private String action;
    /**
     * Map of all available actions.
     */
    private Map<String, BaseAction> actionMap;

    /**
     * Constructor.
     * @param input input interface.
     * @param calculator calculator module.
     */
    public Menu(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
        this.actionMap = new LinkedHashMap<>();
    }

    /**
     * Fill menu with all available actions.
     */
    public void fillMenu() {
        this.addMenuItem("+", new Add("Addition"));
        this.addMenuItem("-", new Subtract("Subtraction"));
        this.addMenuItem("*", new Multiply("Multiplication"));
        this.addMenuItem("/", new Divide("Division"));
        this.addMenuItem("=", new Repeat("Repeat previous operation"));
        this.addMenuItem("c", new Clear("Clear result"));
        this.addMenuItem("h", new Help("Print help"));
        this.addMenuItem("e", new Exit("Exit"));
    }

    /**
     * Add item to menu.
     * @param key key for user input.
     * @param action BaseAction.
     */
    public void addMenuItem(String key, BaseAction action) {
        this.actionMap.put(key, action);
    }

    /**
     * Execute menu command.
     * @param message input command.
     */
    public void compute(String message) {
        if (this.actionMap.containsKey(message)) {
            this.actionMap.get(message).execute();
            if (!message.equals("=")) {
                this.action = message;
            }
        } else {
            error();
        }
    }

    /**
     * Get first argument for operation.
     * @return double value of first argument for operation.
     */
    public double getFirstArg() {
        double firstArg;
        if (this.useResult) {
            firstArg = this.result;
        } else {
            firstArg = Double.parseDouble(this.input.ask("Please enter first argument: "));
        }
        return firstArg;
    }

    /**
     * Get second argument for operation.
     * @return double value of second argument for operation.
     */
    public double getSecondArg() {
        if (!this.repeat) {
            this.secondArg = Double.parseDouble(this.input.ask("Please enter second argument: "));
        }
        return this.secondArg;
    }

    /**
     * Print result of operation.
     * @return print and return result of operation.
     */
    public double getPrintedResult() {
        System.out.println(String.format("Result: %f", this.result));
        return this.result;
    }

    protected void setResult(double result) {
        this.result = result;
    }

    protected void setUseResult(boolean repeat) {
        this.useResult = repeat;
    }

    /**
     * Print error message.
     */
    public void error() {
        System.out.println("Invalid input, please enter h for help...");
    }

    /**
     * Addition.
     */
    private class Add extends BaseAction {

        private Add(String description) {
            super(description);
        }

        @Override
        public void execute() {
            try {
                calculator.add(getFirstArg(), getSecondArg());
                result = calculator.getResult();
                getPrintedResult();
                useResult = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    /**
     * Subtraction.
     */
    private class Subtract extends BaseAction {

        private Subtract(String description) {
            super(description);
        }

        @Override
        public void execute() {
            try {
                calculator.subtract(getFirstArg(), getSecondArg());
                result = calculator.getResult();
                getPrintedResult();
                useResult = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    /**
     * Multiplication.
     */
    private class Multiply extends BaseAction {

        private Multiply(String description) {
            super(description);
        }

        @Override
        public void execute() {
            try {
                calculator.multiple(getFirstArg(), getSecondArg());
                result = calculator.getResult();
                getPrintedResult();
                useResult = true;
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    /**
     * Division.
     */
    private class Divide extends BaseAction {
        private Divide(String description) {
            super(description);
        }

        @Override
        public void execute() {
            double firsArg = getFirstArg();
            double secondArg = getSecondArg();

            if (secondArg != 0) {
                try {
                    calculator.div(firsArg, secondArg);
                    result = calculator.getResult();
                    getPrintedResult();
                    useResult = true;
                } catch (NumberFormatException nfe) {
                    error();
                }
            } else {
                error();
            }
        }
    }

    /**
     * Repeat last operation.
     */
    private class Repeat extends BaseAction {
        private Repeat(String description) {
            super(description);
        }

        @Override
        public void execute() {
            if (useResult) {
                repeat = true;
                compute(action);
                repeat = false;
            } else {
                error();
            }
        }
    }

    /**
     * Clear result, last operation, arguments.
     */
    private class Clear extends BaseAction {

        private Clear(String description) {
            super(description);
        }

        @Override
        public void execute() {
            action = "";
            secondArg = 0;
            result = 0;
            useResult = false;
            repeat = false;
        }
    }

    /**
     * Print help.
     */
    private class Help extends BaseAction {

        private Help(String description) {
            super(description);
        }

        @Override
        public void execute() {
            actionMap.forEach((k, v) -> System.out.println(String.format("%s | %s", k, v.info())));
        }
    }

    /**
     * Exit program.
     */
    private class Exit extends BaseAction {

        private Exit(String description) {
            super(description);
        }

        @Override
        public void execute() {
            return;
        }
    }
}
