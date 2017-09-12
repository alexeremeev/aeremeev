package ru.job4j.chess;

/**
 * class Cell - описание ячейки на шахматной доске.
 */
public class Cell {
    /**
     * Координата по оси X.
     */
    private int xAxis;
    /**
     * Координата по оси Y.
     */
    private int yAxis;

    /**
     * Конструктор.
     * @param xAxis координата по оси X.
     * @param yAxis координата по оси Y.
     */
    public Cell(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    /**
     * Геттер координаты по оси X.
     * @return координата по оси X.
     */
    public int getxAxis() {
        return this.xAxis;
    }

    /**
     * Геттер координаты по оси Y.
     * @return координата по оси Y.
     */
    public int getyAxis() {
        return this.yAxis;
    }
}
