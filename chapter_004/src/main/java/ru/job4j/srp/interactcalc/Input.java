package ru.job4j.srp.interactcalc;

/**
 * Input - user input interface.
 * @author aeremeev
 * @since 06.01.2018
 * @version 1
 */
public interface Input {
    /**
     * Returns string from input.
     * @param question question select.
     * @return string from input.
     */
    String ask(String question);
}
