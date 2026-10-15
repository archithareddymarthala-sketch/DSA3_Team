package com.mediamine.co6;

public class BlellochScan {

    /*
     * Performs an exclusive prefix sum
     * using the Blelloch Scan algorithm.
     *
     * Example:
     *
     * Input:
     * 1 2 3 4
     *
     * Output:
     * 0 1 3 6
     */

    public int[] exclusiveScan(int[] input) {

        int n = input.length;

        int size = 1;

        // Find the next power of 2
        while (size < n) {
            size = size * 2;
        }

        int[] tree =
                new int[size];

        // Copy input
        for (int i = 0;
             i < n;
             i++) {

            tree[i] = input[i];
        }

        // UP-SWEEP PHASE
        for (int step = 1;
             step < size;
             step = step * 2) {

            for (int i = 0;
                 i < size;
                 i = i + 2 * step) {

                int right =
                        i + 2 * step - 1;

                int left =
                        i + step - 1;

                tree[right] =
                        tree[right]
                        + tree[left];
            }
        }

        // Set root to zero
        tree[size - 1] = 0;

        // DOWN-SWEEP PHASE
        for (int step = size / 2;
             step >= 1;
             step = step / 2) {

            for (int i = 0;
                 i < size;
                 i = i + 2 * step) {

                int left =
                        i + step - 1;

                int right =
                        i + 2 * step - 1;

                int temp =
                        tree[left];

                tree[left] =
                        tree[right];

                tree[right] =
                        tree[right]
                        + temp;
            }
        }

        int[] result =
                new int[n];

        for (int i = 0;
             i < n;
             i++) {

            result[i] =
                    tree[i];
        }

        return result;
    }

    public void printArray(
            int[] array) {

        for (int value : array) {

            System.out.print(
                    value + " "
            );
        }

        System.out.println();
    }
}