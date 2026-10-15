package com.mediamine.co6;

public class BlellochScanTest {

    public static void main(String[] args) {

        int[] mediaCounts = {
                1, 2, 3, 4
        };

        BlellochScan scan =
                new BlellochScan();

        System.out.println(
                "========== BLELLOCH SCAN =========="
        );

        System.out.println(
                "Input:"
        );

        scan.printArray(
                mediaCounts
        );

        int[] result =
                scan.exclusiveScan(
                        mediaCounts
                );

        System.out.println();

        System.out.println(
                "Exclusive Prefix Scan:"
        );

        scan.printArray(
                result
        );

        System.out.println();

        System.out.println(
                "Result: Blelloch Scan completed successfully."
        );

        System.out.println(
                "Up-sweep and down-sweep phases were used."
        );
    }
}