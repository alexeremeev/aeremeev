package ru.job4j.srp.interactcalc;

/**
 * InteractCalc - basic calculator with user input.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public class InteractCalc {
    /**
     * Menu.
     */
    private final Menu menu;
    /**
     * Input interface.
     */
    private final Input input;
    /**
     * Exit constant.
     */
    private final static String EXIT = "e";

    /**
     * Constructor.
     * @param menu menu.
     * @param input input interface.
     */
    public InteractCalc(Menu menu, Input input) {
        this.menu = menu;
        this.input = input;
    }

    /**
     * Initialization.
     */
    public void init() {
        String userInput;
        do {
            userInput = this.input.ask("Please enter command: ");
            this.menu.compute(userInput);
        } while (!EXIT.equalsIgnoreCase(userInput));
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Input input = new ConsoleInput();
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        InteractCalc intCalc = new InteractCalc(menu, input);
        menu.compute("h");
        intCalc.init();
    }
}
