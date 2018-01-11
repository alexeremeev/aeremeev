package ru.job4j.elevator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Elevator implements Runnable {

    private long speed;

    private long doorTime;

    private int currentFloor;

    private volatile int destFloor;

    private State state;

    private volatile Set<Integer> requests = new ConcurrentSkipListSet<>();

    private final Object lock = new Object();

    public Elevator(int speed, long doorTime, int currentFloor) {
        this.speed = speed * 1000;
        this.doorTime = doorTime;
        this.currentFloor = currentFloor;
        this.destFloor = currentFloor;
        this.state = State.IDLE;
    }

    public int nextFloor() {
        synchronized (lock) {
            this.state = State.getState(this.currentFloor, this.destFloor);
            if (this.state == State.MOVING_UP) {
                return this.currentFloor + 1;
            } else if (this.state == State.MOVING_DOWN) {
                return this.currentFloor - 1;
            } else {
                return this.currentFloor;
            }
        }
    }

    public void moveElevator() {
        this.state = State.getState(this.currentFloor, this.destFloor);
        System.out.println(String.format("Elevator now on %d floor...", this.currentFloor));
        do {
            try {
                Thread.sleep(this.speed);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            this.currentFloor = nextFloor();
            System.out.println(String.format("Elevator now on %d floor...", this.currentFloor));
        } while (this.currentFloor != this.destFloor);
        this.state = State.IDLE;
        this.doorOpenClose();
    }

    public void doorOpenClose() {
        synchronized (lock) {
            System.out.println("Elevators doors are opened...");
            try {
                Thread.sleep(doorTime);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println("Elevator doors are closed...");
        }
    }


    public void setDestFloor(int destFloor) {
        synchronized (lock) {
            this.destFloor = destFloor;
        }
    }

    @Override
    public void run() {
        System.out.println(String.format("Elevator now on %d floor...", this.currentFloor));
        while (true) {
            this.state = State.getState(this.currentFloor, this.destFloor);
            if (this.state != State.IDLE) {
                this.moveElevator();
            }

//            this.state = State.getState(this.currentFloor, destFloor);
//            if (this.state != State.IDLE) {
//                while (this.currentFloor != destFloor) {
//                    try {
//                        Thread.sleep(this.speed);
//                    } catch (InterruptedException ie) {
//                        ie.printStackTrace();
//                    }
//                    this.currentFloor = nextFloor(destFloor);
//                    System.out.println(String.format("Elevator now on %d floor...", this.currentFloor));
//                    this.state = State.IDLE;
//                    this.doorOpenClose();
//                }
//            }
        }
    }

    public enum State {
        IDLE,
        MOVING_UP,
        MOVING_DOWN;

        public static State getState(int currentFloor, int destFloor) {
            int direction = destFloor - currentFloor;
            if (direction > 0) {
                return MOVING_UP;
            } else if (direction < 0) {
                return MOVING_DOWN;
            } else {
                return IDLE;
            }
        }
    }

}
