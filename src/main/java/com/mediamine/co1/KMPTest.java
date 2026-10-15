package com.mediamine.co1;

public class KMPTest {

    public static void main(String[] args) {

        String text =
                "Artificial intelligence and machine learning are transforming technology.";

        String pattern = "machine";

        boolean result = KMP.search(text, pattern);

        if (result) {
            System.out.println("Pattern found!");
        } else {
            System.out.println("Pattern not found!");
        }
    }
}