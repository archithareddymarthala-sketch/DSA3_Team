package com.mediamine.co5;

public class ThreeSATToClique {

    private int[][] clauses;
    private int[][] graph;

    public ThreeSATToClique(int[][] clauses) {
        this.clauses = clauses;
        this.graph = new int[clauses.length * 3]
                              [clauses.length * 3];
    }

    // Build the graph for the 3-SAT to CLIQUE reduction
    public int[][] buildGraph() {

        int vertices = clauses.length * 3;

        for (int i = 0; i < vertices; i++) {

            for (int j = 0; j < vertices; j++) {

                graph[i][j] = 0;
            }
        }

        for (int clause1 = 0;
             clause1 < clauses.length;
             clause1++) {

            for (int literal1 = 0;
                 literal1 < 3;
                 literal1++) {

                int vertex1 =
                        clause1 * 3 + literal1;

                for (int clause2 = clause1 + 1;
                     clause2 < clauses.length;
                     clause2++) {

                    for (int literal2 = 0;
                         literal2 < 3;
                         literal2++) {

                        int vertex2 =
                                clause2 * 3 + literal2;

                        int value1 =
                                clauses[clause1][literal1];

                        int value2 =
                                clauses[clause2][literal2];

                        if (!isContradictory(
                                value1,
                                value2)) {

                            graph[vertex1][vertex2] = 1;
                            graph[vertex2][vertex1] = 1;
                        }
                    }
                }
            }
        }

        return graph;
    }

    // Check whether two literals contradict each other
    private boolean isContradictory(
            int literal1,
            int literal2) {

        return literal1 == -literal2;
    }

    // Check whether a clique of required size exists
    public boolean hasClique(int targetSize) {

        int[] selected =
                new int[targetSize];

        return findClique(
                selected,
                0,
                0,
                targetSize
        );
    }

    private boolean findClique(
            int[] selected,
            int selectedCount,
            int start,
            int targetSize) {

        if (selectedCount == targetSize) {

            return true;
        }

        int vertices = graph.length;

        for (int vertex = start;
             vertex < vertices;
             vertex++) {

            boolean compatible = true;

            for (int i = 0;
                 i < selectedCount;
                 i++) {

                if (graph[vertex][selected[i]] == 0) {

                    compatible = false;
                    break;
                }
            }

            if (compatible) {

                selected[selectedCount] =
                        vertex;

                if (findClique(
                        selected,
                        selectedCount + 1,
                        vertex + 1,
                        targetSize)) {

                    return true;
                }
            }
        }

        return false;
    }

    // Display the graph
    public void printGraph() {

        System.out.println(
                "========== 3-SAT -> CLIQUE GRAPH =========="
        );

        System.out.println(
                "Adjacency Matrix:"
        );

        for (int i = 0;
             i < graph.length;
             i++) {

            for (int j = 0;
                 j < graph.length;
                 j++) {

                System.out.print(
                        graph[i][j] + " "
                );
            }

            System.out.println();
        }
    }

    // Display the vertices and their literals
    public void printVertices() {

        System.out.println(
                "\nVertices:"
        );

        for (int i = 0;
             i < clauses.length;
             i++) {

            System.out.println(
                    "Clause " + (i + 1)
            );

            for (int j = 0;
                 j < 3;
                 j++) {

                int vertex =
                        i * 3 + j;

                System.out.println(
                        "Vertex "
                        + vertex
                        + " -> Literal "
                        + clauses[i][j]
                );
            }
        }
    }
}