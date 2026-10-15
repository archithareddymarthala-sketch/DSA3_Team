package com.mediamine.co6;

import java.util.Random;

public class RandomizedQuickSort {

    private Random random =
            new Random();

    public void sort(int[] array) {

        quickSort(
                array,
                0,
                array.length - 1
        );
    }

    private void quickSort(
            int[] array,
            int low,
            int high) {

        if (low < high) {

            int pivotIndex =
                    randomizedPartition(
                            array,
                            low,
                            high
                    );

            quickSort(
                    array,
                    low,
                    pivotIndex - 1
            );

            quickSort(
                    array,
                    pivotIndex + 1,
                    high
            );
        }
    }

    private int randomizedPartition(
            int[] array,
            int low,
            int high) {

        int randomIndex =
                low + random.nextInt(
                        high - low + 1
                );

        swap(
                array,
                randomIndex,
                high
        );

        return partition(
                array,
                low,
                high
        );
    }

    private int partition(
            int[] array,
            int low,
            int high) {

        int pivot =
                array[high];

        int i = low - 1;

        for (int j = low;
             j < high;
             j++) {

            if (array[j] <= pivot) {

                i++;

                swap(
                        array,
                        i,
                        j
                );
            }
        }

        swap(
                array,
                i + 1,
                high
        );

        return i + 1;
    }

    private void swap(
            int[] array,
            int i,
            int j) {

        int temp =
                array[i];

        array[i] =
                array[j];

        array[j] =
                temp;
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