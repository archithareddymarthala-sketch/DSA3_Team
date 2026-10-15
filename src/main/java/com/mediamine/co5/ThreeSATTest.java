package com.mediamine.co5;

public class ThreeSATTest {

    public static void main(String[] args) {

        /*
         * MediaMine content constraints
         *
         * Variable 1 = Technology
         * Variable 2 = News
         * Variable 3 = Sports
         *
         * Formula:
         *
         * (A OR B OR C)
         * AND
         * (NOT A OR B OR C)
         * AND
         * (A OR NOT B OR C)
         */

        int[][] clauses = {

                {1, 2, 3},

                {-1, 2, 3},

                {1, -2, 3}
        };

        ThreeSAT threeSAT =
                new ThreeSAT(clauses);

        System.out.println(
                "========== 3-SAT =========="
        );

        System.out.println(
                "MediaMine Content Constraints"
        );

        System.out.println();

        threeSAT.printClauses();

        System.out.println();

        boolean satisfiable =
                threeSAT.solve();

        System.out.println();

        if (satisfiable) {

            System.out.println(
                    "Result: Formula is SATISFIABLE."
            );

        } else {

            System.out.println(
                    "Result: Formula is NOT SATISFIABLE."
            );
        }
    }
}