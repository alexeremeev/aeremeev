package ru.job4j.lift;

import java.util.TreeSet;

/**
 * class Elevator - модель лифта.
 */
public final class Elevator {
    /**
     * Сет запросов-нажатий на кнопки в лифте.
     */
    private TreeSet<Integer> passengersRequests = new TreeSet<>();
    /**
     * Текущий этаж.
     */
    private int currentFloor;
    /**
     * Время открытия - закрытия дверей.
     */
    private long doorTime;
    /**
     * Скорость лифта в м/c.
     */
    private long speed;
    /**
     * Направление движения.
     */
    private Direction direction;
    /**
     * Управляющий тред.
     */
    private Thread requestProcessorThread;

    /**
     * Конструктор.
     * @param speed скорость лифта в м/c.
     * @param doorTime время открытия - закрытия дверей в с.
     * @param currentFloor текущий этаж.
     */
    public Elevator(int speed, int doorTime, int currentFloor) {
        this.currentFloor = currentFloor;
        this.doorTime = doorTime * 1000;
        this.speed = speed * 1000;
        this.direction  = Direction.UP;
    }

    /**
     * Добавить запрос в сет.
     */
    public synchronized void addFloor(int f) {
        passengersRequests.add(f);
        if (requestProcessorThread.getState() == Thread.State.WAITING) {
            notify();
        } else {
            requestProcessorThread.interrupt();
        }

    }
    /**
     * Получить следующий этаж для движения лифта.
     * @return следующий этаж для движения лифта.
     */
    public synchronized int nextFloor() {

        Integer floor;

        if (direction == Direction.UP) {
            if (passengersRequests.ceiling(this.currentFloor) != null) {
                floor = passengersRequests.ceiling(this.currentFloor);
            } else {
                floor = passengersRequests.floor(this.currentFloor);
            }
        } else {
            if (passengersRequests.floor(this.currentFloor) != null) {
                floor = passengersRequests.floor(this.currentFloor);
            } else {
                floor = passengersRequests.floor(this.currentFloor);
            }
        }
        if (floor == null) {
            try {
                System.out.println(String.format("Waiting on %d floor...", this.currentFloor));
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        } else {
            passengersRequests.remove(floor);
        }
        return (floor == null) ? -1 : floor;
    }

    /**
     * Геттер текущего этажа.
     * @return текущий этаж.
     */
    public synchronized int getCurrentFloor() {
        return this.currentFloor;
    }

    /**
     * Сеттер текущего этажа.
     * @param currentFloor значение текущего этажа.
     * @throws InterruptedException InterruptedException.
     */
    public void setCurrentFloor(int currentFloor) throws InterruptedException {
        if (this.currentFloor > currentFloor) {
            setDirection(Direction.DOWN);
        } else {
            setDirection(Direction.UP);
        }
        this.currentFloor = currentFloor;
        System.out.println(String.format("Elevator now on %d floor...", currentFloor));
        Thread.sleep(this.speed);
    }

    /**
     * Открытие-закрытие дверей лифта.
     * @throws InterruptedException InterruptedException.
     */
    public void doorOpenClose() throws InterruptedException {
        System.out.println("Elevator doors are opening...");
        Thread.sleep(this.doorTime / 2);
        System.out.println("Elevator doors are closing...");
        Thread.sleep(this.doorTime / 2);
    }

    /**
     * Сеттер направления движения.
     * @param direction направление движения.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Сеттер управляющего треда.
     * @param requestProcessorThread управляющий тред.
     */
    public void setRequestProcessorThread(Thread requestProcessorThread) {
        this.requestProcessorThread = requestProcessorThread;
    }

    /**
     * Геттер сета запросов к лифту.
     * @return сет запросов к лифту.
     */
    public synchronized TreeSet<Integer> getPassengersRequests() {
        return passengersRequests;
    }


}
