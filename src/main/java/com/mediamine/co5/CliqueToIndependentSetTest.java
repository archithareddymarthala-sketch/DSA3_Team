package com.mediamine.co5;

public class CliqueToIndependentSetTest {

    public static void main(String[] args) {

        /*
         * Example graph obtained from the
         * 3-SAT -> CLIQUE reduction.
         *
         * 9 vertices:
         *
         * 0 1 2  -> Clause 1
         * 3 4 5  -> Clause 2
         * 6 7 8  -> Clause 3
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

        CliqueToIndependentSet reduction =
                new CliqueToIndependentSet(graph);

        System.out.println(
                "========== CLIQUE -> INDEPENDENT SET =========="
        );

        reduction.buildComplementGraph();

        reduction.printComplementGraph();

        System.out.println();

        int requiredSize = 3;

        System.out.println(
                "Required Independent Set Size: "
                + requiredSize
        );

        boolean found =
                reduction.hasIndependentSet(
                        requiredSize
                );

        if (found) {

            System.out.println(
                    "Result: Independent Set of "
                    + "required size exists."
            );

            System.out.println(
                    "CLIQUE -> INDEPENDENT SET "
                    + "reduction successful."
            );

        } else {

            System.out.println(
                    "Result: Required Independent Set "
                    + "does not exist."
            );
        }
    }
}