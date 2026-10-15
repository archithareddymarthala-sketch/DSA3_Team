package com.mediamine.co5;

public class IndependentSetToVertexCover {

    private int[][] graph;
    private int vertices;

    public IndependentSetToVertexCover(int[][] graph) {

        this.graph = graph;
        this.vertices = graph.length;
    }

    /*
     * Relationship:
     *
     * If S is an Independent Set,
     * then V - S is a Vertex Cover.
     *
     * Therefore:
     *
     * Independent Set size = K
     *
     * Vertex Cover size = V - K
     */

    public int calculateVertexCoverSize(
            int independentSetSize) {

        return vertices - independentSetSize;
    }

    // Check whether a vertex cover of the
    // required size exists
    public boolean hasVertexCover(
            int targetSize) {

        int[] selected =
                new int[targetSize];

        return findVertexCover(
                selected,
                0,
                0,
                targetSize
        );
    }

    private boolean findVertexCover(
            int[] selected,
            int selectedCount,
            int start,
            int targetSize) {

        if (selectedCount == targetSize) {

            return coversAllEdges(selected);
        }

        for (int vertex = start;
             vertex < vertices;
             vertex++) {

            selected[selectedCount] =
                    vertex;

            if (findVertexCover(
                    selected,
                    selectedCount + 1,
                    vertex + 1,
                    targetSize)) {

                return true;
            }
        }

        return false;
    }

    // Check whether all edges are covered
    private boolean coversAllEdges(
            int[] selected) {

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = i + 1;
                 j < vertices;
                 j++) {

                if (graph[i][j] == 1) {

                    boolean firstCovered =
                            contains(
                                    selected,
                                    i
                            );

                    boolean secondCovered =
                            contains(
                                    selected,
                                    j
                            );

                    if (!firstCovered &&
                        !secondCovered) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean contains(
            int[] selected,
            int vertex) {

        for (int value : selected) {

            if (value == vertex) {
                return true;
            }
        }

        return false;
    }

    public void printResult(
            int independentSetSize) {

        int vertexCoverSize =
                calculateVertexCoverSize(
                        independentSetSize
                );

        System.out.println(
                "Independent Set Size: "
                + independentSetSize
        );

        System.out.println(
                "Total Vertices: "
                + vertices
        );

        System.out.println(
                "Corresponding Vertex Cover Size: "
                + vertexCoverSize
        );
    }
}