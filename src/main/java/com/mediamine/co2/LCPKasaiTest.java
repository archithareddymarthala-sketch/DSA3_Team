package com.mediamine.co2;

public class LCPKasaiTest {

    public static void main(String[] args) {

        String text = "banana";

        System.out.println("Original Text:");
        System.out.println(text);

        // Build Suffix Array
        int[] suffixArray = SuffixArray.buildSuffixArray(text);

        // Build LCP Array using Kasai's Algorithm
        int[] lcp = LCPKasai.buildLCP(text, suffixArray);

        System.out.println();

        SuffixArray.printSuffixes(text, suffixArray);

        System.out.println();

        LCPKasai.printLCP(text, suffixArray, lcp);
    }
}