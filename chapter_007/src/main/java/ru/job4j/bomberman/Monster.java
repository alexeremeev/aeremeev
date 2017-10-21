package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class Monster - монстр.
 */
public final class Monster extends Hero {
    /**
     * Конструктор.
     * @param name имя.
     * @param gameField игровое поле.
     * @param x начальная координата X.
     * @param y начальная координата Y.
     */
    public Monster(String name, GameField gameField, int x, int y) {
        super(name, gameField, x, y);
    }
    //Переопределяем getNextPosition(): если монстр натыкается на бомбермена, игра завершается.
    @Override
    public boolean getNextPosition(int y, int x) throws InterruptedException {
        boolean result = false;
        ReentrantLock start = position;
        position = gameField.getPosition(y, x);
        if (position != null) {
            result = position.tryLock(500, TimeUnit.MILLISECONDS);
            if (result) {
                start.unlock();
            } else {
                if (gameField.getBombermanPosition() == position) {
                    System.out.println("Killed Bomberman!");
                    gameField.end();
                }
                position = start;
            }
        } else {
            position = start;
        }
        return result;
    }
}
