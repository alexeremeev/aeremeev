package ru.job4j.chess;

/**
 * abstract class Figure - абстрактный класс фигуры на доске.
 */
public abstract class Figure {
    /**
     * Ячейка позиция фигуры на доске.
     */
    private final Cell position;

    /**
     * Конструктор.
     * @param position ячейка позиции фигуры на доске.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Метод для получения пути фигуры на доске.
     * @param dist ячейка назначения.
     * @return массив ячеек Cell[], который должна пройти фигура.
     * @throws ImpossibleMoveException исключение, если фигура не может так ходить по правилам.
     */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Метод, записывающий фигуру в ячейку назначения.
     * @param dist ячейка назначения.
     * @return фигура в новой ячейке.
     */
    public abstract Figure clone(Cell dist);

    /**
     * Метод для получения текущей ячейки фигуры.
     * @return ячейка, в которой находится фигура.
     */
    public Cell occupy() {
        return this.position;
    }

}
