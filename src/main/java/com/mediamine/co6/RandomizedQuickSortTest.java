package com.mediamine.co6;

public class RandomizedQuickSortTest {

    public static void main(String[] args) {

        int[] mediaScores = {
                45, 12, 78, 34, 90, 23, 56, 10
        };

        RandomizedQuickSort quickSort =
                new RandomizedQuickSort();

        System.out.println(
                "========== RANDOMIZED QUICKSORT =========="
        );

        System.out.println(
                "Before Sorting:"
        );

        quickSort.printArray(
                mediaScores
        );

        quickSort.sort(
                mediaScores
        );

        System.out.println();

        System.out.println(
                "After Sorting:"
        );

        quickSort.printArray(
                mediaScores
        );

        System.out.println();

        System.out.println(
                "Result: Media records sorted successfully."
        );

        System.out.println(
                "Randomized pivot selection was used."
        );
    }
}