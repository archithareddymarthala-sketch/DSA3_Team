package com.mediamine.co1;

public class NaiveSearch {

    public static boolean search(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return true;
        }

        if (m > n) {
            return false;
        }

        for (int i = 0; i <= n - m; i++) {

            int j = 0;

            while (j < m &&
                   text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }

            if (j == m) {
                return true;
            }
        }

        return false;
    }
}