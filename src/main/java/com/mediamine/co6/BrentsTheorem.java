package com.mediamine.co6;

public class BrentsTheorem {

    /*
     * Brent's Theorem:
     *
     * T_p <= T_1 / p + T_infinity
     *
     * where:
     *
     * T_p       = execution time using p processors
     * T_1       = total work
     * T_infinity = critical path length
     * p         = number of processors
     *
     * This class calculates the theoretical
     * parallel execution bound.
     */

    public double calculateBound(
            double totalWork,
            double criticalPath,
            int processors) {

        if (processors <= 0) {

            throw new IllegalArgumentException(
                    "Number of processors must be positive."
            );
        }

        if (totalWork < 0 ||
            criticalPath < 0) {

            throw new IllegalArgumentException(
                    "Work and critical path cannot be negative."
            );
        }

        return (totalWork / processors)
                + criticalPath;
    }

    public void printResult(
            double totalWork,
            double criticalPath,
            int processors,
            double bound) {

        System.out.println(
                "========== BRENT'S THEOREM =========="
        );

        System.out.println(
                "Total Work (T1): "
                + totalWork
        );

        System.out.println(
                "Critical Path (T∞): "
                + criticalPath
        );

        System.out.println(
                "Processors (p): "
                + processors
        );

        System.out.println();

        System.out.println(
                "Brent's Theoretical Bound (Tp): "
                + bound
        );

        System.out.println();

        System.out.println(
                "Formula:"
        );

        System.out.println(
                "Tp <= T1 / p + T∞"
        );
    }
}