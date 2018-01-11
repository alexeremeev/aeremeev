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
            Integer floor;
            try {
            floor = this.elevator.nextFloor();
                if (floor >= 1) {
                    while (this.elevator.getCurrentFloor() != this.elevator.nextFloor()) {
                        this.elevator.setCurrentFloor(this.elevator.nextFloor());
                    }
                    this.elevator.doorOpenClose();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
