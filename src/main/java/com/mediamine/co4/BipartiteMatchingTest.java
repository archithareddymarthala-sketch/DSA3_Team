package com.mediamine.co4;

public class BipartiteMatchingTest {

    public static void main(String[] args) {

        String[] mediaNames = {
                "News Article",
                "AI Article",
                "Technology Video",
                "Podcast"
        };

        String[] channelNames = {
                "News Channel",
                "Technology Channel",
                "Video Channel",
                "Audio Channel"
        };

        /*
         * 1 = Media can be assigned
         * 0 = Media cannot be assigned
         *
         *             News Tech Video Audio
         *
         * News         1    0    0     0
         * AI           0    1    1     0
         * Technology   0    1    1     0
         * Podcast      0    0    0     1
         */

        int[][] compatibility = {

                {1, 0, 0, 0},

                {0, 1, 1, 0},

                {0, 1, 1, 0},

                {0, 0, 0, 1}
        };

        BipartiteMatching matching =
                new BipartiteMatching(
                        mediaNames.length,
                        channelNames.length
                );

        System.out.println(
                "========== BIPARTITE MATCHING =========="
        );

        int maximumMatching =
                matching.maximumMatching(
                        compatibility
                );

        System.out.println(
                "Maximum Media-Channel Matching: "
                + maximumMatching
        );

        matching.printMatching(
                compatibility,
                mediaNames,
                channelNames
        );
    }
}