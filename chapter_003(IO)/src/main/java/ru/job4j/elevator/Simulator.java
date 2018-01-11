package ru.job4j.elevator;

import java.util.Scanner;

public class Simulator {

    private Elevator elevator;

    private Building building;

    public Simulator(Elevator elevator, Building building) {
        this.elevator = elevator;
        this.building = building;
    }

    public boolean validateInput() {
        return true;
    }

    public void init() {

    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(2, 1000, 5);
        Thread thread = new Thread(elevator);
        thread.start();
        Scanner scanner = new Scanner(System.in);
        String line;
        int floor;
        while (true) {
            line = scanner.nextLine();
            floor = Integer.valueOf(line);
            elevator.setDestFloor(floor);
        }
    }
}
