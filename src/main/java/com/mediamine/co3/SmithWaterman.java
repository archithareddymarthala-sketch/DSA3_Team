package com.mediamine.co3;

public class SmithWaterman {

    private static final int MATCH = 2;
    private static final int MISMATCH = -1;
    private static final int GAP = -1;

    public static int calculateScore(String sequence1, String sequence2) {

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] dp = new int[m + 1][n + 1];

        int maxScore = 0;

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int diagonal;

                if (sequence1.charAt(i - 1) ==
                    sequence2.charAt(j - 1)) {

                    diagonal = dp[i - 1][j - 1] + MATCH;

                } else {

                    diagonal = dp[i - 1][j - 1] + MISMATCH;
                }

                int up = dp[i - 1][j] + GAP;
                int left = dp[i][j - 1] + GAP;

                // 0 allows a new local alignment to start
                dp[i][j] = Math.max(
                        0,
                        Math.max(
                                diagonal,
                                Math.max(up, left)
                        )
                );

                if (dp[i][j] > maxScore) {
                    maxScore = dp[i][j];
                }
            }
        }

        return maxScore;
    }
}