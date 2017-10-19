package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class Hero - герой.
 */
public final class Hero implements Runnable {
    /**
     * Имя.
     */
    private String name;
    /**
     * Игровое поле.
     */
    private final GameField gameField;
    /**
     * Позиция на игровом поле.
     */
    private ReentrantLock position;
    /**
     * Координата по X.
     */
    private int x;
    /**
     * Координата по Y.
     */
    private int y;
    /**
     * Генератор случайных чисел для направления.
     */
    private static final Random RN = new Random();

    /**
     * Конструктор.
     * @param name имя.
     * @param gameField игровое поле.
     * @param x начальная координата X.
     * @param y начальная координата Y.
     */
    public Hero(String name, GameField gameField, int x, int y) {
        this.name = name;
        this.gameField = gameField;
        this.x = x;
        this.y = y;
        this.position = gameField.getPosition(y, x);
    }

    /**
     * Получение следующей позиции на игровом поле.
     * @param y координата X.
     * @param x координата X.
     * @return true, если клетка найдена и свободна.
     * @throws InterruptedException InterruptedException.
     */
    public boolean getNextPosition(int y, int x) throws InterruptedException {
        boolean result = false;
        ReentrantLock start = position;
        position = gameField.getPosition(y, x);
        if (position != null) {
            result = position.tryLock(500, TimeUnit.MILLISECONDS);
            if (result) {
                start.unlock();
            } else {
                position = start;
            }
        } else {
            position = start;
        }
        return result;
    }

    /**
     * Передвижение героя по игровому полю.
     * @param direction направление движения.
     * @return true, если удалось переместиться.
     * @throws InterruptedException InterruptedException.
     */
    public boolean move(MoveDirection direction) throws InterruptedException {
        boolean result = getNextPosition(this.y + direction.getY(), this.x + direction.getX());
        if (result) {
            this.x += direction.getX();
            this.y += direction.getY();
            System.out.println(String.format("%s moved %s to x = %s y = %s", this.name, direction.toString(), this.x, this.y));
        }
        return result;
    }

    @Override
    public void run() {
        try {
            position.lock();
            while (Thread.currentThread().isAlive()) {
                move(MoveDirection.values()[RN.nextInt(4)]);
                Thread.sleep(500);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
