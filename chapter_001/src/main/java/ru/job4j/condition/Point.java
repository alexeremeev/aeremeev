package ru.job4j.condition;

/**
 * Class Point c проверкой принадлежности прямой в пространстве.
 * @author aeremeev
 * @since 26.08.2017
 * @version 1
 */
public class Point {
    /**
     * параметр x.
     */
    private int x; // x
    /**
     * параметр y.
     */
    private int y; // y

    /**
     * Конструктор Point(x,y).
     * @param x координата x.
     * @param y координата y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер x.
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Геттер y.
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Метод проверки принадлежности точки (x,y) функции y(x)=ax+b.
     * @param a параметр а.
     * @param b параметр b.
     * @return true or false
     */
    public boolean is(int a, int b) {
        return a * getX() + b == getY();
    }
}
