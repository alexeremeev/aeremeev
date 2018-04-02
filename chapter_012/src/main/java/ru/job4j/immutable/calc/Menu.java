package ru.job4j.immutable.calc;

import java.util.*;

/**
 * Class Menu - console menu for calculator.
 * @author aeremeev
 * @since 06.01.2018
 * @version 1
 */
public final class Menu {
    /**
     * Input interface.
     */
    private final Input input;
    /**
     * Calculator module.
     */
    private final Calculator calculator;
    /**
     * Operation order memory;
     */
    private final List<String> memory;
    /**
     * Map of all available actions.
     */
    private final Map<String, BaseAction> actionMap;

    /**
     * Constructor.
     * @param input input interface.
     * @param calculator calculator module.
     */
    public Menu(final Input input, final Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
        this.actionMap = new LinkedHashMap<>();
        this.memory = new LinkedList<>();
    }

    /**
     * Fill menu with all available actions.
     */
    public final void fillMenu() {
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
    public final void addMenuItem(String key, BaseAction action) {
        this.actionMap.put(key, action);
    }

    /**
     * Execute menu command.
     * @param message input command.
     */
    public final void compute(String message) {
        if (this.actionMap.containsKey(message)) {
            this.actionMap.get(message).execute();
        }  else {
            error();
        }
    }

    /**
     * Get first argument for operation.
     * @return double value of first argument for operation.
     */
    public final double getFirstArg() {
        double result;
        if (memory.size() >= 5) {
            result = Double.parseDouble(memory.get(memory.size() - 2));
        } else {
            result = Double.parseDouble(this.input.ask("Please enter first argument: "));
        }
        this.memory.add(String.valueOf(result));
        return result;
    }

    /**
     * Get second argument for operation.
     * @return double value of second argument for operation.
     */
    public final double getSecondArg() {
        double result;
        if (memory.size() >= 5 && memory.get(memory.size() - 1).equals(memory.get(memory.size() - 3))) {
            result = Double.parseDouble(memory.get(memory.size() - 4));
        } else {
            result = Double.parseDouble(this.input.ask("Please enter second argument: "));
        }
        this.memory.add(String.valueOf(result));
        return result;
    }

    public final double getResult() {
        double result = 0D;
        if (!memory.isEmpty()) {
            result = Double.parseDouble(this.memory.get(this.memory.size() - 1));
        }
        return result;
    }

    /**
     * Print result of operation.
     * @return print and return result of operation.
     */
    public final void printResult() {
        double result = Double.parseDouble(this.memory.get(this.memory.size() - 1));
        System.out.println(String.format("Result: %f", result));
    }

    /**
     * Print error message.
     */
    public final void error() {
        System.out.println("Invalid input, please enter h for help...");
        this.memory.clear();
    }

    /**
     * Addition.
     */
    private final class Add extends BaseAction {

        private Add(String description) {
            super(description);
        }

        @Override
        public final void execute() {
            memory.add("+");
            double result = 0D;
            try {
                result = calculator.add(getFirstArg(), getSecondArg());
            } catch (NumberFormatException nfe) {
                error();
            }
            memory.add(String.valueOf(result));
            printResult();
        }
    }

    /**
     * Subtraction.
     */
    private final class Subtract extends BaseAction {

        private Subtract(String description) {
            super(description);
        }

        @Override
        public final void execute() {
            memory.add("-");
            double result = 0D;
            try {
                result = calculator.subtract(getFirstArg(), getSecondArg());
            } catch (NumberFormatException nfe) {
                error();
            }
            memory.add(String.valueOf(result));
            printResult();
        }
    }

    /**
     * Multiplication.
     */
    private final class Multiply extends BaseAction {

        private Multiply(String description) {
            super(description);
        }

        @Override
        public void execute() {
            memory.add("*");
            double result = 0D;
            try {
                result = calculator.multiple(getFirstArg(), getSecondArg());
            } catch (NumberFormatException nfe) {
                error();
            }
            memory.add(String.valueOf(result));
            printResult();
        }
    }

    /**
     * Division.
     */
    private final class Divide extends BaseAction {

        private Divide(String description) {
            super(description);
        }

        @Override
        public void execute() {
            memory.add("/");
            double first = getFirstArg();
            double secondArg = getSecondArg();
            double result = 0D;
            if (secondArg != 0) {
                try {
                    result = calculator.div(first, secondArg);
                } catch (NumberFormatException nfe) {
                    error();
                }
            } else {
                error();
            }
            memory.add(String.valueOf(result));
            printResult();
        }
    }

    /**
     * Repeat last operation.
     */
    private final class Repeat extends BaseAction {

        private Repeat(String description) {
            super(description);
        }

        @Override
        public void execute() {
            if (memory.size() >= 4) {
                String action = memory.get(memory.size() - 4);
                actionMap.get(action).execute();
            }
        }
    }

    /**
     * Clear result, last operation, arguments.
     */
    private final class Clear extends BaseAction {

        private Clear(String description) {
            super(description);
        }

        @Override
        public void execute() {
            memory.clear();
            System.out.println("Memory cleared!");
        }
    }

    /**
     * Print help.
     */
    private final class Help extends BaseAction {

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
    private final class Exit extends BaseAction {

        private Exit(String description) {
            super(description);
        }

        @Override
        public void execute() {
            return;
        }
    }
}
