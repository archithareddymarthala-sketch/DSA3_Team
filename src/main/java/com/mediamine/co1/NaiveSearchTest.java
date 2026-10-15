package com.mediamine.co1;

public class NaiveSearchTest {

    public static void main(String[] args) {

        String text = "Artificial Intelligence and Data Science";
        String pattern = "Intelligence";

        boolean found =
                NaiveSearch.search(text, pattern);

        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Pattern Found: " + found);

        System.out.println();

        String pattern2 = "Machine Learning";

        boolean found2 =
                NaiveSearch.search(text, pattern2);

        System.out.println("Pattern: " + pattern2);
        System.out.println("Pattern Found: " + found2);
    }
}