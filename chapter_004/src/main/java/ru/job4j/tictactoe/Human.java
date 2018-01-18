package ru.job4j.tictactoe;

import java.util.Scanner;

/**
 * Human Player.
 * @author aeremeev
 * @since 17.01.2018
 * @version 1
 */

public class Human extends AbstractPlayer {
    /**
     * Constructor.
     * @param name player's name.
     * @param mark player's mark (X | O).
     * @param field game field.
     */
    public Human(String name, Mark mark, Field field) {
        super(name, mark, field);
    }

    /**
     * Make move on field. Input provided by scanner.
     * If move is not valid, method returns -1.
     * @return cell index.
     */
    @Override
    public int move() {
        int result = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter cell number: ");
        String input = scanner.nextLine();
        if (this.validateNumber(input) && super.getField().validateMove(Integer.valueOf(input))) {
            result = Integer.valueOf(input);
        }
        return result != -1 ? result - 1 : -1;
    }

    /**
     * Validate user input for number.
     * @param input input string.
     * @return true, if user input is correct.
     */
    public boolean validateNumber(String input) {
        String regex = "\\d+";
        return input.matches(regex);
    }
}
