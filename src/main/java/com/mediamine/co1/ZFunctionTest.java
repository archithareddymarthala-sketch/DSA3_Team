package com.mediamine.co1;

public class ZFunctionTest {

    public static void main(String[] args) {

        String text =
                "Artificial intelligence and machine learning are transforming technology.";

        String pattern = "machine";

        boolean result =
                ZFunction.search(text, pattern);

        if (result) {

            System.out.println("Pattern found using Z-Function!");

        } else {

            System.out.println("Pattern not found!");
        }
    }
}