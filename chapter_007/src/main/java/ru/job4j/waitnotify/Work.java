package ru.job4j.waitnotify;

import static java.lang.String.format;

/**
 * class Work - модель Runnable задачи для ThreadPool.
 */
public class Work implements Runnable {
    /**
     * Номер задачи.
     */
    private final int number;
    /**
     * Конструктор.
     * @param number номер задачи.
     */
    public Work(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(format("Work %s is in progress.", number));
    }
}
