package com.mediamine.co2;

import java.util.Arrays;

public class MergeSortTest {

    public static void main(String[] args) {

        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Before Sorting:");
        System.out.println(Arrays.toString(arr));

        MergeSort.sort(arr);

        System.out.println("After Merge Sort:");
        System.out.println(Arrays.toString(arr));
    }
}