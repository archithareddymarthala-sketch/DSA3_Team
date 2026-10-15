package com.mediamine.co2;

import java.util.Arrays;

public class SuffixAutomaton {

    private static class State {

        int length;
        int link;
        int[] next;

        State() {
            length = 0;
            link = -1;
            next = new int[26];

            Arrays.fill(next, -1);
        }
    }

    private State[] states;
    private int size;
    private int last;

    public SuffixAutomaton(String text) {

        states = new State[2 * text.length() + 1];

        for (int i = 0; i < states.length; i++) {
            states[i] = new State();
        }

        size = 1;
        last = 0;

        for (char ch : text.toCharArray()) {
            extend(ch);
        }
    }

    private void extend(char ch) {

        int c = ch - 'a';

        int current = size++;
        states[current].length = states[last].length + 1;

        int p = last;

        while (p != -1 && states[p].next[c] == -1) {
            states[p].next[c] = current;
            p = states[p].link;
        }

        if (p == -1) {

            states[current].link = 0;

        } else {

            int q = states[p].next[c];

            if (states[p].length + 1 == states[q].length) {

                states[current].link = q;

            } else {

                int clone = size++;

                states[clone].length = states[p].length + 1;
                states[clone].link = states[q].link;
                states[clone].next = states[q].next.clone();

                while (p != -1 &&
                       states[p].next[c] == q) {

                    states[p].next[c] = clone;
                    p = states[p].link;
                }

                states[q].link = clone;
                states[current].link = clone;
            }
        }

        last = current;
    }

    public boolean contains(String pattern) {

        int current = 0;

        for (char ch : pattern.toCharArray()) {

            int c = ch - 'a';

            if (c < 0 || c >= 26) {
                return false;
            }

            if (states[current].next[c] == -1) {
                return false;
            }

            current = states[current].next[c];
        }

        return true;
    }
}