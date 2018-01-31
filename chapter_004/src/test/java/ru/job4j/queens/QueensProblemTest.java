package ru.job4j.queens;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * QueensProblem test.
 * @author aeremeev
 * @since 26.01.2018
 * @version 1.1
 */
public class QueensProblemTest {

    private final String separator = System.getProperty("line.separator");

    /**
     * Try to get 2 permutations for 4 queens.
     */
    @Test
    public void thenTryToGetQueensPermutationThenGetCorrectList() {
        StringBuilder first = new StringBuilder();
        first.append("* Q * * ").append(separator);
        first.append("* * * Q ").append(separator);
        first.append("Q * * * ").append(separator);
        first.append("* * Q * ").append(separator);

        StringBuilder second = new StringBuilder();
        second.append("* * Q * ").append(separator);
        second.append("Q * * * ").append(separator);
        second.append("* * * Q ").append(separator);
        second.append("* Q * * ").append(separator);

        List<String> expected = new ArrayList<>(Arrays.asList(first.toString(), second.toString()));

        List<String> actual = new QueensProblem().permute(4);

        assertThat(actual, is(expected));
    }

}