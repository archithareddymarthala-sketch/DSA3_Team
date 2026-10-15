package com.mediamine.co5;

public class ThreeSAT {

    private int[][] clauses;

    /*
     * Each clause contains exactly 3 literals.
     *
     * Positive number  = variable
     * Negative number  = NOT variable
     *
     * Example:
     *
     * {1, 2, 3}
     *
     * means:
     * A OR B OR C
     *
     * {-1, 2, 3}
     *
     * means:
     * NOT A OR B OR C
     */

    public ThreeSAT(int[][] clauses) {
        this.clauses = clauses;
    }

    // Evaluate one literal
    private boolean evaluateLiteral(
            int literal,
            boolean[] assignment) {

        int variable =
                Math.abs(literal) - 1;

        boolean value =
                assignment[variable];

        if (literal < 0) {
            return !value;
        }

        return value;
    }

    // Check whether one clause is satisfied
    private boolean isClauseSatisfied(
            int[] clause,
            boolean[] assignment) {

        for (int literal : clause) {

            if (evaluateLiteral(
                    literal,
                    assignment)) {

                return true;
            }
        }

        return false;
    }

    // Check whether the entire formula is satisfied
    private boolean isFormulaSatisfied(
            boolean[] assignment) {

        for (int[] clause : clauses) {

            if (!isClauseSatisfied(
                    clause,
                    assignment)) {

                return false;
            }
        }

        return true;
    }

    // Brute-force 3-SAT solver
    public boolean solve() {

        int variableCount =
                findVariableCount();

        int totalAssignments =
                1 << variableCount;

        for (int mask = 0;
             mask < totalAssignments;
             mask++) {

            boolean[] assignment =
                    new boolean[variableCount];

            for (int i = 0;
                 i < variableCount;
                 i++) {

                assignment[i] =
                        (mask & (1 << i)) != 0;
            }

            if (isFormulaSatisfied(
                    assignment)) {

                System.out.println(
                        "Satisfying Assignment:"
                );

                printAssignment(
                        assignment
                );

                return true;
            }
        }

        return false;
    }

    // Find the number of variables
    private int findVariableCount() {

        int maximumVariable = 0;

        for (int[] clause : clauses) {

            for (int literal : clause) {

                maximumVariable =
                        Math.max(
                                maximumVariable,
                                Math.abs(literal)
                        );
            }
        }

        return maximumVariable;
    }

    // Display assignment
    public void printAssignment(
            boolean[] assignment) {

        for (int i = 0;
             i < assignment.length;
             i++) {

            System.out.println(
                    "Variable "
                    + (i + 1)
                    + " = "
                    + assignment[i]
            );
        }
    }

    // Display clauses
    public void printClauses() {

        System.out.println(
                "3-SAT Formula:"
        );

        for (int i = 0;
             i < clauses.length;
             i++) {

            System.out.print(
                    "Clause "
                    + (i + 1)
                    + ": "
            );

            for (int j = 0;
                 j < clauses[i].length;
                 j++) {

                System.out.print(
                        clauses[i][j]
                );

                if (j <
                        clauses[i].length - 1) {

                    System.out.print(
                            " OR "
                    );
                }
            }

            System.out.println();
        }
    }
}