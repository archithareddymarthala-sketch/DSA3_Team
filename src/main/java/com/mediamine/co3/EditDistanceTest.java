package com.mediamine.co3;

public class EditDistanceTest {

    public static void main(String[] args) {

        String word1 = "kitten";
        String word2 = "sitting";

        int distance = EditDistance.calculate(word1, word2);

        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);
        System.out.println("Edit Distance: " + distance);

        System.out.println();

        String query = "artifical inteligence";
        String correct = "artificial intelligence";

        int queryDistance =
                EditDistance.calculate(query, correct);

        System.out.println("Search Query: " + query);
        System.out.println("Correct Text: " + correct);
        System.out.println("Edit Distance: " + queryDistance);
    }
}