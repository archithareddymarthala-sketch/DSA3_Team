package com.mediamine.co5;

public class ThreeSATToCliqueTest {

    public static void main(String[] args) {

        /*
         * 3-SAT Formula:
         *
         * (A OR B OR C)
         * AND
         * (NOT A OR B OR C)
         * AND
         * (A OR NOT B OR C)
         *
         * A = Variable 1
         * B = Variable 2
         * C = Variable 3
         */

        int[][] clauses = {

                {1, 2, 3},

                {-1, 2, 3},

                {1, -2, 3}
        };

        ThreeSATToClique reduction =
                new ThreeSATToClique(clauses);

        System.out.println(
                "========== 3-SAT -> CLIQUE =========="
        );

        reduction.buildGraph();

        reduction.printVertices();

        System.out.println();

        reduction.printGraph();

        System.out.println();

        int requiredCliqueSize =
                clauses.length;

        System.out.println(
                "Required Clique Size: "
                + requiredCliqueSize
        );

        boolean found =
                reduction.hasClique(
                        requiredCliqueSize
                );

        if (found) {

            System.out.println(
                    "Result: Clique of required size exists."
            );

            System.out.println(
                    "Therefore, the 3-SAT instance "
                    + "has a valid CLIQUE reduction."
            );

        } else {

            System.out.println(
                    "Result: Required clique does not exist."
            );
        }
    }
}