package ru.job4j.condition;
/**
 * Class Triangle c проверкой существования и вычисления площади треугольника.
 * @author aeremeev
 * @since 27.08.2017
 * @version 1
 */
public class Triangle {
    /**
     * Вершина a.
     */
    private Point a;
    /**
     * Вершина b.
     */
    private Point b;
    /**
     * Вершина c.
     */
    private Point c;

    /**
     * Конструктор для треугольника по вершинам.
     * @param a вершина a.
     * @param b вершина b.
     * @param c вершина c.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод подсчета расстояния между двумя точками на плоскости.
     * @param left точка слева.
     * @param right точка справа.
     * @return расстояние между left и right.
     */
    public double distance(Point left, Point right) {
        return Math.sqrt((right.getX() - left.getX()) * (right.getX() - left.getX()) + (right.getY() - left.getY()) * (right.getY() - left.getY()));
    }

    /**
     * Метод подсчета периметра трегольника.
     * @param ab расстояние между точками a и b.
     * @param ac расстояние между точками a и с.
     * @param bc расстояние между точками b и c.
     * @return периметр треугольника.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод вычисления площади треугольника.
     * @return площадь треугольника, если выполняется условие существования exist, или -1.
     */
    public double area() {
        double rsl = -1;
        double ab = this.distance(this.a, this.b);
        double ac = this.distance(this.a, this.c);
        double bc = this.distance(this.b, this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Условие существования треугольника (кроме вырожденного): сумма двух любых сторон больше третьей.
     * @param ab расстояние между точками a и b.
     * @param ac расстояние между точками a и с.
     * @param bc расстояние между точками b и c.
     * @return true если существует.
     */
    public boolean exist(double ab, double ac, double bc) {
        return (ab + ac > bc) && (ab + bc > ac) && (ac + bc > ab);
    }
}
