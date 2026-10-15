package com.mediamine.co4;

import java.util.Arrays;

public class BipartiteMatching {

    private int mediaCount;
    private int channelCount;

    public BipartiteMatching(
            int mediaCount,
            int channelCount) {

        this.mediaCount = mediaCount;
        this.channelCount = channelCount;
    }

    // Find an augmenting path
    private boolean findMatch(
            int media,
            boolean[] visited,
            int[][] compatibility,
            int[] channelMatch) {

        for (int channel = 0;
             channel < channelCount;
             channel++) {

            if (compatibility[media][channel] == 1
                    && !visited[channel]) {

                visited[channel] = true;

                if (channelMatch[channel] == -1
                        || findMatch(
                                channelMatch[channel],
                                visited,
                                compatibility,
                                channelMatch)) {

                    channelMatch[channel] = media;

                    return true;
                }
            }
        }

        return false;
    }

    // Bipartite matching algorithm
    public int maximumMatching(
            int[][] compatibility) {

        int[] channelMatch =
                new int[channelCount];

        Arrays.fill(
                channelMatch,
                -1
        );

        int matching = 0;

        for (int media = 0;
             media < mediaCount;
             media++) {

            boolean[] visited =
                    new boolean[channelCount];

            if (findMatch(
                    media,
                    visited,
                    compatibility,
                    channelMatch)) {

                matching++;
            }
        }

        return matching;
    }

    // Print the actual assignments
    public void printMatching(
            int[][] compatibility,
            String[] mediaNames,
            String[] channelNames) {

        int[] channelMatch =
                new int[channelCount];

        Arrays.fill(
                channelMatch,
                -1
        );

        for (int media = 0;
             media < mediaCount;
             media++) {

            boolean[] visited =
                    new boolean[channelCount];

            findMatch(
                    media,
                    visited,
                    compatibility,
                    channelMatch
            );
        }

        System.out.println(
                "\nMedia-to-Channel Assignments:"
        );

        for (int channel = 0;
             channel < channelCount;
             channel++) {

            if (channelMatch[channel] != -1) {

                System.out.println(
                        mediaNames[channelMatch[channel]]
                        + " -> "
                        + channelNames[channel]
                );
            }
        }
    }
}