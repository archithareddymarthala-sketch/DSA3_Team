package com.mediamine.co3;

public class MatrixChainDP {

    public static int findMinimumMultiplications(int[] dimensions) {

        int n = dimensions.length - 1;

        int[][] dp = new int[n][n];

        // length is the chain length
        for (int length = 2; length <= n; length++) {

            for (int i = 0; i <= n - length; i++) {

                int j = i + length - 1;

                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    int cost =
                            dp[i][k]
                            + dp[k + 1][j]
                            + dimensions[i]
                            * dimensions[k + 1]
                            * dimensions[j + 1];

                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}