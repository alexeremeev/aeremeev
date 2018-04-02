package ru.job4j.immutable.calc;

import java.util.Scanner;

/**
 * ConsoleInput - console input to calculator via Scanner.
 * @author aeremeev
 * @since 06.01.2018
 * @version 1
 */
public final class ConsoleInput implements Input {
    /**
     * Scanner.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Returns string from user input.
     * @param question question select.
     * @return string from user input.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
