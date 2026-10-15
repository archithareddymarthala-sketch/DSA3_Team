package com.mediamine.co2;

public class SuffixArrayTest {

    public static void main(String[] args) {

        String text = "banana";

        System.out.println("Original Text:");
        System.out.println(text);

        int[] suffixArray = SuffixArray.buildSuffixArray(text);

        System.out.println();

        SuffixArray.printSuffixes(text, suffixArray);
    }
}