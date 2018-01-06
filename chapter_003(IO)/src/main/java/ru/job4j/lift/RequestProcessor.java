package ru.job4j.lift;

/**
 * class RequestProcessor - управляющий тред лифта.
 */
public final class RequestProcessor implements Runnable {
    /**
     * Модель лифта.
     */
    private Elevator elevator;

    /**
     * Конструктор.
     * @param elevator модель лифта.
     */
    RequestProcessor(final Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        while (true) {
            int floor = this.elevator.nextFloor();
            int currentFloor = this.elevator.getCurrentFloor();
            try {
                if (floor >= 1) {
                    if (currentFloor > floor) {
                        while (currentFloor > floor) {
                            this.elevator.setCurrentFloor(--currentFloor);
                        }
                    } else {
                        while (currentFloor < floor) {
                            this.elevator.setCurrentFloor(++currentFloor);
                        }
                    }
                    this.elevator.doorOpenClose();
                }
            } catch (InterruptedException e) {
                if (this.elevator.getCurrentFloor() != floor) {
                    this.elevator.getPassengersRequests().add(floor);
                }
            }
        }
    }
}
