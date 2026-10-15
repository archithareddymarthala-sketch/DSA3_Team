package com.mediamine.co1;

public class RabinKarpTest {

    public static void main(String[] args) {

        String text =
                "Artificial intelligence and machine learning are transforming technology.";

        String pattern = "machine";

        boolean result =
                RabinKarp.search(text, pattern);

        if (result) {

            System.out.println(
                    "Pattern found using Rabin-Karp!");

        } else {

            System.out.println(
                    "Pattern not found!");
        }
    }
}