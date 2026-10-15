package com.mediamine.co6;

public class ParallelReduceTest {

    public static void main(String[] args) {

        int[] mediaScores = {
                10, 20, 30, 40, 50, 60
        };

        int threadCount = 3;

        ParallelReduce reduce =
                new ParallelReduce();

        System.out.println(
                "========== PARALLEL REDUCE =========="
        );

        System.out.println(
                "Media Scores:"
        );

        for (int value : mediaScores) {

            System.out.print(
                    value + " "
            );
        }

        System.out.println();

        System.out.println();

        System.out.println(
                "Number of Threads: "
                + threadCount
        );

        long result =
                reduce.sum(
                        mediaScores,
                        threadCount
                );

        System.out.println();

        System.out.println(
                "Parallel Sum: "
                + result
        );

        System.out.println();

        System.out.println(
                "Result: Parallel reduction completed successfully."
        );

        System.out.println(
                "Multiple threads calculated partial sums."
        );
    }
}