package com.mediamine.co3;

public class NeedlemanWunsch {

    private static final int MATCH = 1;
    private static final int MISMATCH = -1;
    private static final int GAP = -2;

    public static int calculateScore(String sequence1, String sequence2) {

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize first row
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j * GAP;
        }

        // Initialize first column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i * GAP;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int matchScore;

                if (sequence1.charAt(i - 1) ==
                    sequence2.charAt(j - 1)) {

                    matchScore = MATCH;

                } else {

                    matchScore = MISMATCH;
                }

                int diagonal =
                        dp[i - 1][j - 1] + matchScore;

                int up =
                        dp[i - 1][j] + GAP;

                int left =
                        dp[i][j - 1] + GAP;

                dp[i][j] = Math.max(
                        diagonal,
                        Math.max(up, left)
                );
            }
        }

        return dp[m][n];
    }
}