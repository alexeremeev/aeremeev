package ru.job4j.lift;

import java.util.Scanner;

/**
 * class RequestListener - постоянно ожидает ввод этажа от пользователя.
 */
public final class RequestListener implements Runnable {
    /**
     * Модель лифта.
     */
    private Elevator elevator;
    /**
     * Модель здания.
     */
    private Building building;

    /**
     * Конструктор.
     * @param elevator модель лифта.
     * @param building модель здания.
     */
    RequestListener(final Elevator elevator, final Building building) {
        this.elevator = elevator;
        this.building = building;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;
            while (true) {
                System.out.println(String.format("Please enter floor number from 1 to %d:", this.building.getFloorsCount()));
                userInput = scanner.next();
                if (isValidFloorNumber(userInput)) {
                    System.out.println(String.format("Passenger pressed: %s floor", userInput));
                    this.elevator.addFloor(Integer.parseInt(userInput));
                } else {
                    System.out.println(String.format("Invalid input: %s",  userInput));
                }
            }
        }
    }

    /**
     * Проверить введеный пользователем этаж на корректность.
     * @param userInput введеный пользователем этаж.
     * @return true, если ввод от пользователя одно или два числа и этаж есть в здании.
     */
    private boolean isValidFloorNumber(String userInput) {
        boolean result = false;
        int floor;
        if ((userInput != null) && userInput.matches("\\d{1,2}")) {
            floor = Integer.parseInt(userInput);
            result = this.building.validateFloor(floor);
        }
        return result;
    }
}
