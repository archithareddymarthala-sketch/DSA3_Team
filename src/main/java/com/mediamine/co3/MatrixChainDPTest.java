package com.mediamine.co3;

public class MatrixChainDPTest {

    public static void main(String[] args) {

        // A1 = 10x20
        // A2 = 20x30
        // A3 = 30x40
        int[] dimensions = {10, 20, 30, 40};

        int minimumCost =
                MatrixChainDP.findMinimumMultiplications(dimensions);

        System.out.println("Matrix Dimensions:");
        System.out.println("A1 = 10 x 20");
        System.out.println("A2 = 20 x 30");
        System.out.println("A3 = 30 x 40");

        System.out.println();
        System.out.println(
                "Minimum Multiplication Cost: " + minimumCost
        );
    }
}