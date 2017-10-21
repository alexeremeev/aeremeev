package ru.job4j.bomberman;

/**
 * class Bomberman - bomberman.
 */
public final class Bomberman extends Hero {
    /**
     * Конструктор.
     * @param name имя.
     * @param gameField игровое поле.
     * @param x координата X.
     * @param y координата Y.
     */
    public Bomberman(String name, GameField gameField, int x, int y) {
        super(name, gameField, x, y);
    }
}
