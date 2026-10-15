package com.mediamine.co2;

import java.util.Arrays;
import java.util.Comparator;

public class SuffixArray {

    public static int[] buildSuffixArray(String text) {

        int n = text.length();

        Integer[] suffixes = new Integer[n];

        // Store starting positions of all suffixes
        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        // Sort suffixes lexicographically
        Arrays.sort(suffixes, new Comparator<Integer>() {

            @Override
            public int compare(Integer a, Integer b) {
                return text.substring(a).compareTo(text.substring(b));
            }
        });

        // Convert Integer[] to int[]
        int[] suffixArray = new int[n];

        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i];
        }

        return suffixArray;
    }

    public static void printSuffixes(String text, int[] suffixArray) {

        System.out.println("Suffix Array:");

        for (int i = 0; i < suffixArray.length; i++) {

            int index = suffixArray[i];

            System.out.println(
                index + " -> " + text.substring(index)
            );
        }
    }
}