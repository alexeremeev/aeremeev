package ru.job4j.bomberman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
     * ExecutorService для выполения нескольких поток.
     */
    private final ExecutorService executor;
    /**
     * Массив монстров.
     */
    private final Monster[] monsters;
    /**
     * Сложность - размер массива монстров.
     */
    private final int difficulty;
    /**
     * Счетчик монстров.
     */
    private int counterOfMonsters = 0;
    /**
     * Основной герой - bomberman.
     */
    private Bomberman bomberman;

    /**
     * Конструктор.
     * @param height высота.
     * @param width ширина.
     * @param difficulty сложность.
     */
    public GameField(int height, int width, int difficulty) {
        this.height = height;
        this.width = width;
        this.board = new ReentrantLock[height][width];
        for (int i = 0; i != height; i++) {
            for (int j = 0; j != width; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
        this.difficulty = difficulty;
        this.monsters = new Monster[difficulty];
        executor = Executors.newCachedThreadPool();
    }

    /**
     * Получение текущей позиции бомбермена.
     * @return текущая позиция бомбермена.
     */
    public ReentrantLock getBombermanPosition() {
        return this.bomberman.getPosition();
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
     * Добавляет бомбермена на игровое поле.
     * @param name имя героя.
     * @param x координата X.
     * @param y координата Y.
     * @return true, если добавлен.
     */
    public boolean addBomberMan(String name, int x, int y) {
        boolean result = false;
        if (x < this.width && x >= 0 && y < this.height && y >= 0) {
           this.bomberman = new Bomberman(name, this, x, y);
           result = true;
        }
        return result;
    }

    /**
     * Добавление монстров на игровое поле.
     * @param name имя монстра.
     * @param x координата X.
     * @param y координата Y.
     * @return true, если добавлен.
     */
    public boolean addMonsters(String name, int x, int y) {
        boolean result = false;
        if (x < this.width && x >= 0 && y < this.height && y >= 0 && this.counterOfMonsters < this.difficulty) {
            this.monsters[counterOfMonsters++] = new Monster(name, this, x, y);
            result = true;
        }
        return result;
    }

    /**
     * Добавление заблокированных блоков на игровое поле.
     * @param x координата X.
     * @param y координата Y.
     * @return true, если добавлен.
     */
    public boolean addBlockedSections(int x, int y) {
        boolean result = false;
        if (x < this.width && x >= 0 && y < this.height && y >= 0) {
            board[y][x].lock();
            result = true;
        }
        return result;
    }

    /**
     * Запуск игры.
     */
    public void start() {
        executor.submit(this.bomberman);
        for (int index = 0; index != this.monsters.length; index++) {
            executor.submit(this.monsters[index]);
        }
    }

    /**
     * Окончание игры.
     */
    public void end() {
        System.out.println("Boberman is dead! Game over!");
        executor.shutdownNow();
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        GameField field = new GameField(8, 8, 3);
        field.addBomberMan("Bomberman without bombs", 0, 0);
        field.addMonsters("Average creep 1", 4, 4);
        field.addMonsters("Average creep 2", 0, 7);
        field.addMonsters("Average creep 3", 7, 0);
        field.addBlockedSections(7, 7);
        field.addBlockedSections(6, 6);
        field.addBlockedSections(6, 5);
        field.start();
    }
}
