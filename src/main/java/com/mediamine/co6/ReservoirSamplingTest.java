package com.mediamine.co6;

public class ReservoirSamplingTest {

    public static void main(String[] args) {

        int[] mediaIds = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10
        };

        int sampleSize = 3;

        ReservoirSampling sampling =
                new ReservoirSampling();

        System.out.println(
                "========== RESERVOIR SAMPLING =========="
        );

        System.out.println(
                "Media IDs:"
        );

        for (int id : mediaIds) {

            System.out.print(
                    id + " "
            );
        }

        System.out.println();

        System.out.println();

        int[] sample =
                sampling.sample(
                        mediaIds,
                        sampleSize
                );

        System.out.println(
                "Random Sample of "
                + sampleSize
                + " Media IDs:"
        );

        sampling.printSample(
                sample
        );

        System.out.println();

        System.out.println(
                "Result: Random media sample generated successfully."
        );

        System.out.println(
                "Reservoir Sampling was used."
        );
    }
}