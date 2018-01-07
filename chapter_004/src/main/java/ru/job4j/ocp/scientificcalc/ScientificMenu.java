package ru.job4j.ocp.scientificcalc;

import ru.job4j.srp.interactcalc.BaseAction;
import ru.job4j.srp.interactcalc.Calculator;
import ru.job4j.srp.interactcalc.Input;
import ru.job4j.srp.interactcalc.Menu;

/**
 * ScientificMenu - console menu for Scientific calculator.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public class ScientificMenu extends Menu {

    /**
     * Constructor.
     * @param input input interface.
     * @param calculator calculator module.
     */
    public ScientificMenu(Input input, Calculator calculator) {
        super(input, calculator);
    }

    @Override
    public void fillMenu() {
        super.fillMenu();
        addMenuItem("sin", new Sine("Sine, input value in degrees"));
        addMenuItem("cos", new Cosine("Cosine, input value in degrees"));
        addMenuItem("tan", new Tangent("Tangent, input value in degrees"));
    }

    /**
     * Sine.
     */
    private class Sine extends BaseAction {
        public Sine(String description) {
            super(description);
        }

        @Override
        public void execute() {
            try {
                setResult(Math.sin(Math.toRadians(getFirstArg())));
                getPrintedResult();
                setUseResult(true);
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    /**
     * Cosine.
     */
    private class Cosine extends BaseAction {
        public Cosine(String description) {
            super(description);
        }

        @Override
        public void execute() {
            try {
                setResult(Math.cos(Math.toRadians(getFirstArg())));
                getPrintedResult();
                setUseResult(true);
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }

    /**
     * Tangent.
     */
    private class Tangent extends BaseAction {
        public Tangent(String description) {
            super(description);
        }

        @Override
        public void execute() {
            try {
                setResult(Math.tan(Math.toRadians(getFirstArg())));
                getPrintedResult();
                setUseResult(true);
            } catch (NumberFormatException nfe) {
                error();
            }
        }
    }
}
