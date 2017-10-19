package ru.job4j.bomberman;

/**
 * enum MoveDirection - перечисление направлений движения с оффсетами коодинат.
 */
public enum MoveDirection {
    /**
     * Возможные пути движения.
     */
    UP (-1, 0), LEFT (0, -1), DOWN (1, 0), RIGHT (0, 1);
    /**
     * Оффсет по оси Х.
     */
    private final int x;
    /**
     * Оффсет по оси Y.
     */
    private final int y;

    /**
     * Конструктор.
     * @param x оффсет по Х.
     * @param y оффсет по Y.
     */
    MoveDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер по оси X.
     * @return оффсет по оси X.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Геттер по оси Y.
     * @return оффсет по оси Y.
     */
    public int getY() {
        return this.y;
    }
}
