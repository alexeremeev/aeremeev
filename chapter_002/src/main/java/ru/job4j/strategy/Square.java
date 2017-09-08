package ru.job4j.strategy;

/**
 * Рисуем квадрат.
 */
public class Square implements Shape {
    /**
     * Реализация метода pic интерфейса Shape.
     * @return строка с квадратом.
     */
    public String pic() {
        StringBuilder drawSquare = new StringBuilder();
        final String line = System.getProperty("line.separator");
        drawSquare.append("X X X X X").append(line);
        drawSquare.append("X       X").append(line);
        drawSquare.append("X       X").append(line);
        drawSquare.append("X       X").append(line);
        drawSquare.append("X X X X X").append(line);
        return drawSquare.toString();
    }
}
