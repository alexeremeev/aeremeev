package ru.job4j.chess;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты перемещения и исключения для фигуры слон.
 */
public class BishopTest {
    /**
     * Тест перемещения из 1с в 4f.
     */
    @Test
    public void tryMoveBishopFrom1cTo4f() {
        Board board = new Board();
        Cell source = new Cell(7, 2);
        Cell dist = new Cell(4, 5);
        board.put(new Bishop(source));
        assertThat(board.move(source, dist), is(true));
    }

    /**
     * Тест выбрасывания исключения OccupiedWayException.
     * При перемещении из из 1с в 4f путь перекрыт другим слоном на 3е.
     * @throws OccupiedWayException bсключение, если путь фигуры перекрыт другой.
     */
    @Test(expected = OccupiedWayException.class)
    public void moveBishopToOccupiedCell() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(7, 2);
        Cell block = new Cell(6, 3);
        Cell dist = new Cell(4, 5);
        Figure firstBishop = new Bishop(source);
        Figure secondBishop = new Bishop(block);
        board.put(firstBishop);
        board.put(secondBishop);
        assertThat(board.move(source, dist), is(true));
    }

    /**
     * Тест выбрасывания исключения FigureNotFoundException.
     * Фигуры в ячейке fake нет.
     * @throws FigureNotFoundException исключение, если в source не найдена фигура.
     */
    @Test(expected = FigureNotFoundException.class)
    public void tryMoveFigureFromEmptyCell() throws FigureNotFoundException {
        Board board = new Board();
        Cell fake = new Cell(6, 3);
        Cell dist = new Cell(4, 5);
        assertThat(board.move(fake, dist), is(true));
    }

    /**
     * Тест выбрасывания исключения ImpossibleMoveException.
     * Пытаемся переместить слона из 1с в 1а.
     * @throws ImpossibleMoveException исключение, если фигура не может так ходить по правилам.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void tryIllegalMoveForBishop() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(7, 2);
        Cell dist = new Cell(7, 0);
        board.put(new Bishop(source));
        assertThat(board.move(source, dist), is(true));
    }
}
