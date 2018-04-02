package ru.job4j.immutable.calc;


public final class ImmutableCalc {
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
    public ImmutableCalc(final Menu menu, final Input input) {
        this.menu = menu;
        this.input = input;
    }

    /**
     * Initialization.
     */
    public final void init() {
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
        ImmutableCalc intCalc = new ImmutableCalc(menu, input);
        menu.compute("h");
        intCalc.init();
    }
}
