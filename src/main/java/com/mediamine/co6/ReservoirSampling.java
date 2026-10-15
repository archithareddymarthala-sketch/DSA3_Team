package com.mediamine.co6;

import java.util.Random;

public class ReservoirSampling {

    private Random random =
            new Random();

    /*
     * Select k random items from an array
     * using Reservoir Sampling.
     */
    public int[] sample(
            int[] data,
            int k) {

        if (k <= 0 ||
            k > data.length) {

            throw new IllegalArgumentException(
                    "Invalid sample size."
            );
        }

        int[] reservoir =
                new int[k];

        // Fill the reservoir
        // with the first k elements
        for (int i = 0;
             i < k;
             i++) {

            reservoir[i] =
                    data[i];
        }

        // Process remaining elements
        for (int i = k;
             i < data.length;
             i++) {

            int randomIndex =
                    random.nextInt(i + 1);

            if (randomIndex < k) {

                reservoir[randomIndex] =
                        data[i];
            }
        }

        return reservoir;
    }

    public void printSample(
            int[] sample) {

        for (int value : sample) {

            System.out.print(
                    value + " "
            );
        }

        System.out.println();
    }
}