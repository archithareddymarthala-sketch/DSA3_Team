package com.mediamine.co3;

public class BitmaskDPTest {

    public static void main(String[] args) {

        int[] values = {10, 20, 15, 8};

        int maxItems = 2;

        int bestValue =
                BitmaskDP.findBestSelection(values, maxItems);

        System.out.println("Category Values:");
        System.out.println("News = 10");
        System.out.println("Technology = 20");
        System.out.println("Science = 15");
        System.out.println("Sports = 8");

        System.out.println();
        System.out.println("Maximum Categories Allowed: " + maxItems);
        System.out.println("Best Total Value: " + bestValue);
    }
}