package ru.job4j.strategy;

/**
 * Рисуем треугольник.
 */
public class Triangle implements Shape {
    /**
     * Реализация метода pic интерфейса Shape.
     * @return строка с треугольником.
     */
    public String pic() {
        StringBuilder drawTriangle = new StringBuilder();
        final String line = System.getProperty("line.separator");
        drawTriangle.append("  X  ").append(line);
        drawTriangle.append(" X X ").append(line);
        drawTriangle.append("X X X").append(line);
        return drawTriangle.toString();
    }
}
