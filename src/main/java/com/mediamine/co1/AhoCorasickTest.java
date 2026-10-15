package com.mediamine.co1;

import java.util.List;

public class AhoCorasickTest {

    public static void main(String[] args) {

        AhoCorasick ahoCorasick =
                new AhoCorasick();


        // Add multiple keywords
        ahoCorasick.addKeyword("java");
        ahoCorasick.addKeyword("machine");
        ahoCorasick.addKeyword("technology");
        ahoCorasick.addKeyword("artificial intelligence");


        // Build failure links
        ahoCorasick.build();


        String text =
                "Artificial intelligence and machine learning " +
                "are transforming technology using Java.";


        // Search all keywords
        List<String> found =
                ahoCorasick.search(text);


        System.out.println("Keywords found:");

        for (String keyword : found) {

            System.out.println(keyword);
        }
    }
}