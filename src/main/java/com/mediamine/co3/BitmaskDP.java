package com.mediamine.co3;

public class BitmaskDP {

    /*
     * Finds the maximum total value of categories
     * that can be selected within a given limit.
     *
     * Each category is represented by one bit in a mask.
     */

    public static int findBestSelection(
            int[] values,
            int maxItems) {

        int n = values.length;

        int totalMasks = 1 << n;

        int bestValue = 0;

        for (int mask = 0; mask < totalMasks; mask++) {

            int count = 0;
            int totalValue = 0;

            for (int i = 0; i < n; i++) {

                // Check whether item i is selected
                if ((mask & (1 << i)) != 0) {

                    count++;
                    totalValue += values[i];
                }
            }

            if (count <= maxItems &&
                totalValue > bestValue) {

                bestValue = totalValue;
            }
        }

        return bestValue;
    }
}