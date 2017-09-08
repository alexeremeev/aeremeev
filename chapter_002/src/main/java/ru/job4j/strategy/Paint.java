package ru.job4j.strategy;

/**
 * Class Paint - рисуем фигуры в консоль.
 */
public class Paint {
    /**
     * Метод вывода фигур в консоль.
     * @param shape фигура.
     */
    public void draw(Shape shape) {
        System.out.print(shape.pic());
    }
}
