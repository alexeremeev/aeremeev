package ru.job4j.register;

import java.util.Scanner;

/**
 * Start. Register demo.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public class Start {
    /**
     * Exit constant.
     */
    private final static String EXIT = "exit";
    /**
     * Register.
     */
    private final Register register;
    /**
     * Console UI.
     */
    private final ConsoleUI consoleUI;
    /**
     * Scanner of user input.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructor.
     * @param register register.
     * @param consoleUI console UI.
     */
    public Start(Register register, ConsoleUI consoleUI) {
        this.register = register;
        this.consoleUI = consoleUI;
    }

    /**
     * Initialization.
     */
    public void init() {
        this.consoleUI.fillActions();
    }

    /**
     * Demo method.
     */
    public void demo() {
        String input;
        do {
            this.consoleUI.showActions();
            System.out.println("Enter menu item:");
            input = sc.nextLine();
            if (consoleUI.getActions().containsKey(input)) {
                this.consoleUI.getActions().get(input).execute(this.register);
            }
        } while (!EXIT.equalsIgnoreCase(input));
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Register register = new SimpleRegister();

        Thread producer = new Thread(new Producer(register));
        producer.start();

        Start start = new Start(register, new ConsoleUI());
        start.init();
        start.demo();
        producer.interrupt();
    }
}
