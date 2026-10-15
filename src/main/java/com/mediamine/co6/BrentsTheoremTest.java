package com.mediamine.co6;

public class BrentsTheoremTest {

    public static void main(String[] args) {

        BrentsTheorem brent =
                new BrentsTheorem();

        double totalWork = 100;
        double criticalPath = 10;
        int processors = 4;

        System.out.println(
                "========== BRENT'S THEOREM =========="
        );

        double bound =
                brent.calculateBound(
                        totalWork,
                        criticalPath,
                        processors
                );

        brent.printResult(
                totalWork,
                criticalPath,
                processors,
                bound
        );

        System.out.println();

        System.out.println(
                "Result: Brent's theorem analysis "
                + "completed successfully."
        );
    }
}
