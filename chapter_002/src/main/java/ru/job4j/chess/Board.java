package ru.job4j.chess;
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
    private int coutner = 0;

    /**
     * Добавление новой фигуры на доску (в массив).
     * @param figure фигура.
     */
    public void put(Figure figure) {
        this.figures[coutner++] = figure;
    }

    /**
     * Проверка, есть ли фигура на заданной ячейке.
     * @param source ячейка на доске.
     * @return номер счетчика найденной фигуры в массиве figures[], если не найдено, то -1.
     */
    public int isOnCell(Cell source) {
        int occupied = -1;
        int xSource = source.getxAxis();
        int ySource = source.getyAxis();
        for (int index = 0; index != figures.length; index++) {
            if (figures[index] != null) {
                int xFigure = figures[index].occupy().getxAxis();
                int yFigure = figures[index].occupy().getyAxis();
                if (xSource == xFigure && ySource == yFigure) {
                    occupied = index;
                    break;
                }
            }
        }
        return occupied;
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
        Cell[] path = figures[findFigure].way(dist);
        for (Cell cell : path) {
            if (isOnCell(cell) != -1) {
                throw new OccupiedWayException("Figure's path is blocked by another figure!");
            }
        }
        figures[findFigure] = figures[findFigure].clone(dist);
        return true;
    }

}
