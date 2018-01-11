package ru.job4j.lift;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * class Elevator - модель лифта.
 */
public final class Elevator {
    /**
     * Priory request queue with comparator.
     * Sort ascending when elevator moves up and vice versa.
     */
    private PriorityBlockingQueue<Integer> passengersRequests = new PriorityBlockingQueue<>(20, new Comparator<Integer>() {
        @Override

        public int compare(Integer floorA, Integer floorB) {
            if (direction == Direction.UP) {
                if (floorA < floorB) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (direction == Direction.DOWN) {
                if (floorA < floorB) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }
    });
    /**
     * Текущий этаж.
     */
    private AtomicInteger currentFloor = new AtomicInteger();
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
     * Конструктор.
     * @param speed скорость лифта в м/c.
     * @param doorTime время открытия - закрытия дверей в с.
     * @param currentFloor текущий этаж.
     */
    public Elevator(int speed, int doorTime, int currentFloor) {
        this.currentFloor.set(currentFloor);
        this.doorTime = doorTime * 1000;
        this.speed = speed * 1000;
        this.direction = Direction.UP;
    }

    /**
     * Добавить запрос в сет.
     */
    public void addFloor(int floor) {
        if (!passengersRequests.contains(floor)) {
            passengersRequests.put(floor);
        }

    }
    /**
     * Получить следующий этаж для движения лифта.
     * @return следующий этаж для движения лифта.
     */
    public int nextFloor() throws InterruptedException {

        Integer floor;

        floor = passengersRequests.peek();
            if (floor != null && floor == this.currentFloor.get()) {
                passengersRequests.remove(floor);
            }
        return (floor == null) ? -1 : floor;
    }

    /**
     * Геттер текущего этажа.
     * @return текущий этаж.
     */
    public int getCurrentFloor() {
        return this.currentFloor.get();
    }

    /**
     * Сеттер текущего этажа.
     * @param currentFloor значение текущего этажа.
     * @throws InterruptedException InterruptedException.
     */
    public int setCurrentFloor(int currentFloor) throws InterruptedException {
        if (this.currentFloor.get()> currentFloor) {
            setDirection(Direction.DOWN);
            this.currentFloor.getAndDecrement();
        } else {
            setDirection(Direction.UP);
            this.currentFloor.getAndIncrement();
        }
        //this.currentFloor.set(currentFloor);
        System.out.println(String.format("Elevator now on %d floor...", this.currentFloor.get()));
        Thread.sleep(this.speed);
        return this.currentFloor.get();
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
        if (this.passengersRequests.isEmpty()) {
            System.out.println(String.format("Waiting on %d floor...", this.currentFloor.get()));
        }
    }

    /**
     * Сеттер направления движения.
     * @param direction направление движения.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
