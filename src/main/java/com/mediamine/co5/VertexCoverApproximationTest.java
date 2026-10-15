package com.mediamine.co5;

import java.util.List;

public class VertexCoverApproximationTest {

    public static void main(String[] args) {

        /*
         * Example graph representing
         * relationships between media nodes.
         */

        int[][] graph = {

                {0, 1, 1, 0, 0, 0},

                {1, 0, 1, 1, 0, 0},

                {1, 1, 0, 0, 1, 0},

                {0, 1, 0, 0, 1, 1},

                {0, 0, 1, 1, 0, 1},

                {0, 0, 0, 1, 1, 0}
        };

        VertexCoverApproximation approximation =
                new VertexCoverApproximation(graph);

        System.out.println(
                "========== VERTEX COVER 2-APPROXIMATION =========="
        );

        List<Integer> vertexCover =
                approximation.approximateVertexCover();

        approximation.printVertexCover(
                vertexCover
        );

        System.out.println();

        System.out.println(
                "Result: Approximate Vertex Cover "
                + "successfully generated."
        );

        System.out.println(
                "Guarantee: Solution size is at most "
                + "2 times the optimal solution."
        );
    }
}