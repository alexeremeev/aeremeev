package ru.job4j.flood;

import java.util.*;

/**
 * class FloodFill - решение задачи с заполнением фигуры.
 * Задан двойной массив, заполненный нулями и единицами. Нужно определить самое большое множество единиц.
 * Множеством считается объединение единиц, которые соприкасаются друг с другом.
 * Диагональное соприкосновение не учитывать.
 */
public class FloodFill {
    /**
     * Возвращает список координат клеток самой большой фигуры из 1.
     * @param field - двумерный массив 0 и 1.
     * @return список координат клеток самой большой фигуры из 1.
     */
    public List<String> largestFilledObject(int[][] field) {

        Cell[][] cells = new Cell[field.length][field[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (field[i][j] != 0) {
                    cells[i][j] = new Cell(i, j, false);
                }
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i != cells.length; i++) {
            for (int j = 0; j != cells[i].length; j++) {
                if (cells[i][j] != null && !cells[i][j].marked) {
                    Queue<Cell> queue = new LinkedList<>();
                    List<String> filled = new ArrayList<>();
                    cells[i][j].marked = true;
                    Cell current;
                    queue.add(cells[i][j]);
                    while ((current = queue.poll()) != null) {

                        filled.add(current.toString());
                        moveToAdjacent(cells, queue, current.x + 1, current.y);
                        moveToAdjacent(cells, queue, current.x, current.y + 1);
                        moveToAdjacent(cells, queue, current.x - 1, current.y);
                        moveToAdjacent(cells, queue, current.x, current.y - 1);

                }
                    if (filled.size() > result.size()) {
                        result = filled;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Перемещение в прилегающую клетку поля.
     * @param cells массив доступных клеток.
     * @param queue очередь, для реализации BFS в графе.
     * @param x координата X.
     * @param y координата Y.
     */
    private void moveToAdjacent(Cell[][] cells, Queue<Cell> queue, int x, int y) {
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) {
            return;
        }
        if (!cells[x][y].marked) {
            cells[x][y].marked = true;
            queue.add(cells[x][y]);
        }

        }

    /**
     * Клетка на поле двумерного массива.
     */
    private class Cell {
        /**
         * Координата X.
         */
        private Integer x;
        /**
         * Координата Y.
         */
        private Integer y;
        /**
         * Заливка клетки.
         */
        boolean marked;

        /**
         * Конструктор.
         * @param x координата X.
         * @param y координата Y.
         * @param marked заливка клетки.
         */
        private Cell(int x, int y, boolean marked) {
            this.x = x;
            this.y = y;
            this.marked = marked;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Cell cell = (Cell) o;

            if (x != cell.x) {
                return false;
            }
            if (y != cell.y) {
                return false;
            }
            return marked == cell.marked;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + (marked ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", this.x, this.y);
        }
    }
}