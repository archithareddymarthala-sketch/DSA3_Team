package com.mediamine.co5;

public class IndependentSetToVertexCoverTest {

    public static void main(String[] args) {

        /*
         * Example graph with 9 vertices.
         *
         * We assume an Independent Set
         * of size 3.
         *
         * Therefore:
         *
         * Vertex Cover Size = 9 - 3 = 6
         */

        int[][] graph = {

                {0, 1, 1, 0, 1, 1, 1, 1, 1},

                {1, 0, 1, 1, 0, 1, 1, 1, 1},

                {1, 1, 0, 1, 1, 0, 1, 1, 1},

                {0, 1, 1, 0, 1, 1, 1, 1, 1},

                {1, 0, 1, 1, 0, 1, 1, 1, 1},

                {1, 1, 0, 1, 1, 0, 1, 1, 1},

                {1, 1, 1, 1, 1, 1, 0, 1, 1},

                {1, 1, 1, 1, 1, 1, 1, 0, 1},

                {1, 1, 1, 1, 1, 1, 1, 1, 0}
        };

        IndependentSetToVertexCover reduction =
                new IndependentSetToVertexCover(graph);

        System.out.println(
                "========== INDEPENDENT SET -> VERTEX COVER =========="
        );

        int independentSetSize = 3;

        reduction.printResult(
                independentSetSize
        );

        System.out.println();

        int vertexCoverSize =
                reduction.calculateVertexCoverSize(
                        independentSetSize
                );

        System.out.println(
                "Checking Vertex Cover..."
        );

        boolean found =
                reduction.hasVertexCover(
                        vertexCoverSize
                );

        if (found) {

            System.out.println(
                    "Result: Vertex Cover of required size exists."
            );

            System.out.println(
                    "Independent Set -> Vertex Cover "
                    + "reduction successful."
            );

        } else {

            System.out.println(
                    "Result: Required Vertex Cover "
                    + "does not exist."
            );
        }
    }
}