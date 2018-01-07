package ru.job4j.srp.interactcalc;

/**
 * StubInput - input from string array for junit tests.
 * @author aeremeev
 * @since 06.01.2018
 * @version 1
 */
public class StubInput implements Input {
    /**
     * Array of user inputs.
     */
    private String[] inputs;
    /**
     * Starting index.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param inputs array of user inputs.
     */
    public StubInput(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public String ask(String question) {
        return inputs[index++];
    }
}
