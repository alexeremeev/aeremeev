package ru.job4j.control;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса MazeSolver.
 */
public class MazeSolverTest {
    /**
     * Тест прохождения лабиринта.
     */
    @Test
    public void whenMoveThroughMazeThenGetShortestPath() {
        MazeSolver ms = new MazeSolver();
        int[][] maze = new int[][] {
                {1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 2}};

        List<String> actual = ms.findShortestPath(maze);

        List<String> expected = new LinkedList<>(Arrays.asList("(0, 0)", "(1, 0)", "(2, 0)", "(3, 0)", "(3, 1)",
                "(3, 2)", "(3, 3)", "(3, 4)", "(3, 5)", "(4, 5)"));

        assertThat(actual, is(expected));


    }

}
