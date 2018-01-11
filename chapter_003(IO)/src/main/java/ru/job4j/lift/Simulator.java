package ru.job4j.lift;

/**
 * Симулятор лифта в здании.
 */
public class Simulator {
    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {

        Building building = new Building(20, 6);

        int speed = 2;

        Elevator elevator = new Elevator(building.getFloorHeight() / speed, 4, 1);
        System.out.println("Elevator is working...");

        Thread requestListenerThread = new Thread(new RequestListener(elevator, building));

        Thread requestProcessorThread = new Thread(new RequestProcessor(elevator));

        requestListenerThread.start();
        requestProcessorThread.start();

    }
}





