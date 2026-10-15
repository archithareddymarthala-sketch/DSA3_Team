package com.mediamine.co3;

public class SmithWatermanTest {

    public static void main(String[] args) {

        String sequence1 = "ACACACTA";
        String sequence2 = "AGCACACA";

        int score =
                SmithWaterman.calculateScore(
                        sequence1,
                        sequence2
                );

        System.out.println("Sequence 1: " + sequence1);
        System.out.println("Sequence 2: " + sequence2);
        System.out.println("Local Alignment Score: " + score);
    }
}