package ru.job4j.control;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class MazeSolver - поиск и вывод кратчайшего пути в лабиринте по алгоритму Ли.
 * В данном случае нужно переместиться из верхнего левого угла в нижний правый.
 * Разрешены движения вниз и вправо.
 */
public class MazeSolver {
    /**
     * Возвращает строковый список координат кратчайшего пути в лабиринте.
     * @param maze двумерный бинарный лабиринт. Разрешеные для перемещения блоки помечены 1,
     *             запрещенные 0, выход из лабиринта 2.
     * @return строковый список координат кратчайшего пути в лабиринте,
     *         если решения не найдено - возвращает пустой список.
     */
    public  List<String> findShortestPath(int[][] maze) {

        Cell[][] cells = new Cell[maze.length][maze[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (maze[i][j] != 0) {
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
                }
            }
        }

        LinkedList<Cell> queue = new LinkedList<>();
        Cell start = cells[0][0];
        start.distance = 0;
        queue.add(start);

        Cell destination = null;
        Cell current;

        while ((current = queue.poll()) != null) {
            if (maze[current.x][current.y] == 2) {
                destination = current;
            }
            moveToAdjacent(cells, queue, current.x + 1, current.y, current);
            moveToAdjacent(cells, queue, current.x, current.y + 1, current);
//            moveToAdjacent(cells, queue, current.x - 1, current.y, current);
//            moveToAdjacent(cells, queue, current.x, current.y - 1, current);
        }

        if (destination == null) {
            return Collections.emptyList();
        } else {
            LinkedList<String> path = new LinkedList<>();
            current = destination;
            path.addFirst(current.toString());
            while ((current = current.previous) != null) {
                path.addFirst(current.toString());
            }
            return path;
        }
    }

    /**
     * Перемещение в прилегающую клетку лабиринта. Устанавливаем в них дистанцию на +1 от клетки перемещения.
     * @param cells массив клеток доступных перемещений в лабиринте.
     * @param queue очередь, для реализации BFS в графе.
     * @param x координата X.
     * @param y координата Y.
     * @param parent клетка, из которой перемещаемся.
     */
    private void moveToAdjacent(Cell[][] cells, LinkedList<Cell> queue, int x, int y, Cell parent) {
        int distance = parent.distance + 1;
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) {
            return;
        }
        Cell current = cells[x][y];
        if (distance < current.distance) {
            current.distance = distance;
            current.previous = parent;
            queue.add(current);
        }
    }

    /**
     * Клетка в лабиринте.
     */
    private class Cell {
        /**
         * Координата X.
         */
        private int x;
        /**
         * Координата Y.
         */
        private int y;
        /**
         * Дистанция от начала лабиринта.
         */
        private int distance;
        /**
         * Предыдущая клетка.
         */
        private Cell previous;

        /**
         * Конструктор.
         * @param x координата X.
         * @param y координата Y.
         * @param distance дистанция от начала лабиринта.
         * @param previous предыдущая клетка.
         */
        private Cell(int x, int y, int distance, Cell previous) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.previous = previous;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", this.x, this.y);
        }
    }
}