package ru.job4j.chess;

/**
 * class Bishop - фигура слон.
 */
public class Bishop extends Figure {
    /**
     * Конструктор.
     * @param position ячейка позиции фигуры на доске.
     */
    Bishop(Cell position) {
        super(position);
    }

    /**
     * Реализация метода way() абстрактного класса Figure.
     * @param dist ячейка назначения.
     * @return массив ячеек path[], который пройдет слон.
     * @throws ImpossibleMoveException исключение, если фигура не может так ходить по правилам.
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        int startxAxis = this.occupy().getxAxis();
        int startyAxis = this.occupy().getyAxis();
        int endxAxis = dist.getxAxis();
        int endyAxis = dist.getyAxis();
        int distance = Math.abs(startxAxis - endxAxis);
        Cell[] path = new Cell[distance];
        if (Math.abs(startxAxis - endxAxis) == Math.abs(startyAxis - endyAxis)) {
            for (int index = 0; index != distance; index++) {
                if (startxAxis > endxAxis) {
                    if (startyAxis > endyAxis) {
                        path[index] = new Cell(startxAxis - 1 - index, startyAxis - 1 - index);
                    } else {
                        path[index] = new Cell(startxAxis - 1 - index, startyAxis + 1 + index);
                    }
                } else {
                    if (startyAxis < endyAxis) {
                        path[index] = new Cell(startxAxis + 1 + index, startyAxis + 1 + index);
                    } else {
                        path[index] = new Cell(startxAxis + 1 + index, startyAxis - 1 - index);
                    }
                }
            }
            return path;
        } else {
            throw new ImpossibleMoveException("You can't move bishop this way!");
        }
    }

    /**
     * Реализация метода clone() абстрактного класса Figure.
     * @param dist ячейка назначения.
     * @return слон в новой ячейке.
     */
    @Override
    public Figure clone(Cell dist) {
        return new Bishop(dist);
    }
}
