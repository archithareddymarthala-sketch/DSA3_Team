package com.mediamine.co2;

public class SuffixAutomatonTest {

    public static void main(String[] args) {

        String text = "banana";

        SuffixAutomaton automaton = new SuffixAutomaton(text);

        System.out.println("Original Text:");
        System.out.println(text);

        System.out.println();

        System.out.println("Searching substrings:");

        System.out.println("ana -> " + automaton.contains("ana"));
        System.out.println("ban -> " + automaton.contains("ban"));
        System.out.println("nana -> " + automaton.contains("nana"));
        System.out.println("xyz -> " + automaton.contains("xyz"));
    }
}