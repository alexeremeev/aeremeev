package ru.job4j.structures.arrays;

public class ApproxStringEquality {

    public boolean approxEquality(String original, String toMatch) {

        boolean result = false;
        if (original.length() != toMatch.length() && this.calculate(original, toMatch) < 2) {
            result = true;
        } else if (this.calculate(original, toMatch) < 3) {
            result = true;
        }
        return result;
    }

    /**
     * Returns number of operations, based on Levenshtein distance algorithm, needed to equalize two strings.
     * @param original string to match.
     * @param toMatch string to match.
     * @return number of operations, based on Levenshtein distance algorithm, needed to equalize two strings.
     */
    public int calculate(String original, String toMatch) {

        int[][] dp = new int[original.length() + 1][toMatch.length() + 1];

        for (int i = 0; i <= original.length(); i++) {
            for (int j = 0; j <= toMatch.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = this.min(new int[]{dp[i - 1][j - 1] + this.delta(original.charAt(i - 1), toMatch.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1});
                }
            }
        }
        return dp[original.length()][toMatch.length()];
    }


    private int min(int[] ints) {
        if (ints.length == 0) {
            throw new IllegalArgumentException("Int[] ints is empty.");
        }

        int min = ints[0];

        for (int i = 1; i < ints.length; ++i) {
            if (min > ints[i]) {
                min = ints[i];
            }
        }

        return min;
    }

    private int delta(char a, char b) {
        return a == b ? 0 : 1;
    }
}
