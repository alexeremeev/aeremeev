package ru.job4j.ocp.scientificcalc;

import ru.job4j.srp.interactcalc.*;
/**
 * ScientificCalc - scientific calculator with user input.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public class ScientificCalc extends InteractCalc {
    /**
     * Constructor
     * @param menu menu.
     * @param input input interface.
     */
    public ScientificCalc(Menu menu, Input input) {
        super(menu, input);
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Input input = new ConsoleInput();
        Menu menu = new ScientificMenu(input, calculator);
        menu.fillMenu();
        ScientificCalc scientificCalc = new ScientificCalc(menu, input);
        menu.compute("h");
        scientificCalc.init();
    }
}
