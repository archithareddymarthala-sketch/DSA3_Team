package com.mediamine.co5;

public class CliqueToIndependentSet {

    private int[][] graph;
    private int[][] complementGraph;

    public CliqueToIndependentSet(int[][] graph) {

        this.graph = graph;

        int vertices = graph.length;

        complementGraph =
                new int[vertices][vertices];
    }

    // Build the complement graph
    public int[][] buildComplementGraph() {

        int vertices = graph.length;

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = 0;
                 j < vertices;
                 j++) {

                if (i != j &&
                    graph[i][j] == 0) {

                    complementGraph[i][j] = 1;

                } else {

                    complementGraph[i][j] = 0;
                }
            }
        }

        return complementGraph;
    }

    // Print the complement graph
    public void printComplementGraph() {

        System.out.println(
                "========== CLIQUE -> INDEPENDENT SET =========="
        );

        System.out.println(
                "Complement Graph:"
        );

        for (int i = 0;
             i < complementGraph.length;
             i++) {

            for (int j = 0;
                 j < complementGraph.length;
                 j++) {

                System.out.print(
                        complementGraph[i][j]
                        + " "
                );
            }

            System.out.println();
        }
    }

    // Check whether an independent set of
    // the required size exists
    public boolean hasIndependentSet(
            int targetSize) {

        int[] selected =
                new int[targetSize];

        return findIndependentSet(
                selected,
                0,
                0,
                targetSize
        );
    }

    private boolean findIndependentSet(
            int[] selected,
            int selectedCount,
            int start,
            int targetSize) {

        if (selectedCount == targetSize) {

            return true;
        }

        int vertices =
                complementGraph.length;

        for (int vertex = start;
             vertex < vertices;
             vertex++) {

            boolean compatible = true;

            for (int i = 0;
                 i < selectedCount;
                 i++) {

                if (complementGraph
                        [vertex]
                        [selected[i]] == 1) {

                    compatible = false;
                    break;
                }
            }

            if (compatible) {

                selected[selectedCount] =
                        vertex;

                if (findIndependentSet(
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
}