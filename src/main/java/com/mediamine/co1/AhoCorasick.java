package com.mediamine.co1;

import java.util.*;

public class AhoCorasick {

    // Trie node
    private static class Node {

        Map<Character, Node> children =
                new HashMap<>();

        Node failure;

        List<String> outputs =
                new ArrayList<>();
    }


    private final Node root;


    public AhoCorasick() {
        root = new Node();
        root.failure = root;
    }


    // Add a keyword
    public void addKeyword(String keyword) {

        keyword = keyword.toLowerCase();

        Node current = root;

        for (char ch : keyword.toCharArray()) {

            current.children.putIfAbsent(
                    ch,
                    new Node()
            );

            current = current.children.get(ch);
        }

        current.outputs.add(keyword);
    }


    // Build failure links
    public void build() {

        Queue<Node> queue =
                new LinkedList<>();

        for (Node child : root.children.values()) {

            child.failure = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry
                    : current.children.entrySet()) {

                char ch = entry.getKey();

                Node child = entry.getValue();

                Node failure =
                        current.failure;

                while (failure != root &&
                       !failure.children.containsKey(ch)) {

                    failure = failure.failure;
                }

                if (failure.children.containsKey(ch)
                        && failure.children.get(ch) != child) {

                    child.failure =
                            failure.children.get(ch);

                } else {

                    child.failure = root;
                }

                child.outputs.addAll(
                        child.failure.outputs
                );

                queue.add(child);
            }
        }
    }


    // Search multiple keywords
    public List<String> search(String text) {

        text = text.toLowerCase();

        List<String> found =
                new ArrayList<>();

        Node current = root;

        for (char ch : text.toCharArray()) {

            while (current != root &&
                   !current.children.containsKey(ch)) {

                current = current.failure;
            }

            if (current.children.containsKey(ch)) {

                current =
                        current.children.get(ch);
            }

            found.addAll(current.outputs);
        }

        return found;
    }
}