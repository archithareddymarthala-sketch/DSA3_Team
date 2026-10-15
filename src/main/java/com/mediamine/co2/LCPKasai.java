package com.mediamine.co2;

public class LCPKasai {

    public static int[] buildLCP(String text, int[] suffixArray) {

        int n = text.length();

        int[] rank = new int[n];
        int[] lcp = new int[n];

        // Build rank array
        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int k = 0;

        // Kasai's Algorithm
        for (int i = 0; i < n; i++) {

            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = suffixArray[rank[i] + 1];

            while (i + k < n &&
                   j + k < n &&
                   text.charAt(i + k) == text.charAt(j + k)) {

                k++;
            }

            lcp[rank[i]] = k;

            if (k > 0) {
                k--;
            }
        }

        return lcp;
    }

    public static void printLCP(
            String text,
            int[] suffixArray,
            int[] lcp) {

        System.out.println("LCP Array:");

        for (int i = 0; i < lcp.length; i++) {

            System.out.println(
                "Suffix: " + text.substring(suffixArray[i])
                + " | LCP = " + lcp[i]
            );
        }
    }
}