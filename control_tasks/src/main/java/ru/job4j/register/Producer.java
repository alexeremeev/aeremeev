package ru.job4j.register;

import ru.job4j.register.model.SimpleEvent;

import java.util.Random;

/**
 * Producer. Randomly creates and adds events to register.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public final class Producer implements Runnable {
    /**
     * Register.
     */
    private final Register register;
    /**
     * Random.
     */
    private final Random random = new Random();
    /**
     * ID pointer.
     */
    private int id = 0;

    /**
     * Constructor.
     * @param register register.
     */
    public Producer(Register register) {
        this.register = register;
    }

    /**
     * Adds event to register.
     */
    public void add() {
        this.register.add(new SimpleEvent(id++, System.currentTimeMillis()));
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            this.add();
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println("Finishing...");
                break;
            }
        }
    }
}
