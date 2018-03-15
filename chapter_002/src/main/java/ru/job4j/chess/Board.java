package ru.job4j.chess;

import java.util.stream.IntStream;

/**
 * class Board - описание шахматной доски и ее методов.
 */
public class Board {
    /**
     * Массив фигур на доске.
     */
    private Figure[] figures = new Figure[6];
    /**
     * Счетчик фигур в массиве.
     */
    private int counter = 0;

    /**
     * Добавление новой фигуры на доску (в массив).
     * @param figure фигура.
     */
    public void put(Figure figure) {
        this.figures[this.counter++] = figure;
    }

    /**
     * Проверка, есть ли фигура на заданной ячейке.
     * @param source ячейка на доске.
     * @return номер счетчика найденной фигуры в массиве figures[], если не найдено, то -1.
     */
    public int isOnCell(Cell source) {
        int xSource = source.getxAxis();
        int ySource = source.getyAxis();
        return IntStream.range(0, this.figures.length).
                filter(i -> this.figures[i] != null && this.figures[i].occupy().getxAxis() == xSource &&
                        this.figures[i].occupy().getyAxis() == ySource).findFirst().orElse(-1);
    }

    /**
     * Перемещение фигуры на доске.
     * @param source исходная ячейка.
     * @param dist ячейка назначения.
     * @return true, если фигура может пройти.
     * @throws ImpossibleMoveException исключение, если фигура не может так ходить по правилам.
     * @throws OccupiedWayException исключение, если путь фигуры перекрыт другой.
     * @throws FigureNotFoundException исключение, если в source не найдена фигура.
     */
    public boolean move(Cell source, Cell dist) throws
            ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int findFigure = isOnCell(source);
        if (isOnCell(source) == -1) {
            throw new FigureNotFoundException("Figure not found in source cell!");
        }
        Cell[] path = this.figures[findFigure].way(dist);
        for (Cell cell : path) {
            if (isOnCell(cell) != -1) {
                throw new OccupiedWayException("Figure's path is blocked by another figure!");
            }
        }
        this.figures[findFigure] = this.figures[findFigure].clone(dist);
        return true;
    }

}
