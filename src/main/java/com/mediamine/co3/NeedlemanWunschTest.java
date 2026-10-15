package com.mediamine.co3;

public class NeedlemanWunschTest {

    public static void main(String[] args) {

        String sequence1 = "GATTACA";
        String sequence2 = "GCATGCU";

        int score =
                NeedlemanWunsch.calculateScore(
                        sequence1,
                        sequence2
                );

        System.out.println("Sequence 1: " + sequence1);
        System.out.println("Sequence 2: " + sequence2);
        System.out.println("Global Alignment Score: " + score);
    }
}