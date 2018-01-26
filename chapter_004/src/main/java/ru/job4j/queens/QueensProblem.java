package ru.job4j.queens;

import java.util.ArrayList;
import java.util.List;

/**
 * N Queen problem puzzle.
 * @author aeremeev
 * @since 26.01.2018
 * @version 1
 */
public class QueensProblem {
    /**
     * List of all permutations.
     */
    private List<String> allPermutations;

    /**
     * Constructor.
     * @param allPermutations list of all permutations.
     */
    public QueensProblem(List<String> allPermutations) {
        this.allPermutations = allPermutations;
    }

    /**
     * Get all permutations.
     * @return list of all permutations.
     */
    public List<String> getAllPermutations() {
        return this.allPermutations;
    }

    /**
     * Check if placement of queen don't have chance to capture another queen.
     * @param queens queens placement array.
     * @param n placement of queen in array.
     * @return true, if queens[n] can't capture another queens.
     */
    public boolean notCapturing(int[] queens, int n) {
        boolean result = true;
        for (int i = 0; i != n; i++) {
            if ((queens[i] == queens[n]) || Math.abs(queens[i] - queens[n]) == (n - i)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Add all permutations to list allPermutations using recursive backtracking.
     * @param queensCount count of queens.
     */
    public void permute(int queensCount) {
        int[] queens = new int[queensCount];
        this.permute(queens, 0);
    }

    /**
     * Method tries to put queen on field in "k" place. If k == queens.length, then permutation added to list.
     * @param queens queens placement array.
     * @param k place on field.
     */
    private void permute(int[] queens, int k) {
        if (k == queens.length) {
            this.allPermutations.add(queensToString(queens));
        } else {
            for (int i = 0; i < queens.length; i++) {
                queens[k] = i;
                if (notCapturing(queens, k)) {
                    this.permute(queens, k + 1);
                }
            }
        }
    }

    /**
     * Get string representation of chess field with Q as queen and * as empty cell.
     * @param queens queens placement array.
     * @return string representation of chess field with queens array placement.
     */
    public String queensToString(int[] queens) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i != queens.length; i++) {
            for (int j = 0; j != queens.length; j++) {
                if (queens[i] == j) {
                    result.append("Q ");
                } else {
                    result.append("* ");
                }
            }
            result.append(System.getProperty("line.separator"));
        }
        return result.toString();
    }

    /**
     * Main
     * @param args args.
     */
    public static void main(String[] args) {
        QueensProblem problem = new QueensProblem(new ArrayList<>());
        problem.permute(8);
        List<String> permutations = problem.getAllPermutations();
        for (String field: permutations) {
            System.out.println(field);
        }
    }

}
