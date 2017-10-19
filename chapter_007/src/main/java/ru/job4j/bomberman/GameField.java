package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * class GameField - игровое поле.
 */
public final class GameField {
    /**
     * Массив ReentrantLock клеток поля.
     */
    private final ReentrantLock[][] board;
    /**
     * Высота поля.
     */
    private final int height;
    /**
     * Ширина поля.
     */
    private final int width;

    /**
     * Конструктор.
     * @param height высота.
     * @param width ширина.
     */
    public GameField(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new ReentrantLock[height][width];
        for (int i = 0; i != height; i++) {
            for (int j = 0; j != width; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Получение клетки на игровом поле.
     * @param height высота.
     * @param width ширина.
     * @return ReentrantLock, если координаты попадают в массив игрового поля, иначе - null.
     */
    public ReentrantLock getPosition(int height, int width) {
        ReentrantLock lock = null;
        if (height < this.height && height >= 0 && width < this.width && width >= 0) {
            lock = board[height][width];
        }
        return lock;
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        GameField field = new GameField(4, 4);
        Thread hero = new Thread(new Hero("Creep", field, 0, 0));
        hero.start();
    }
}
