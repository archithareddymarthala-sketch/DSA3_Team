package com.mediamine.co5;

import java.util.ArrayList;
import java.util.List;

public class VertexCoverApproximation {

    private int[][] graph;
    private int vertices;

    public VertexCoverApproximation(int[][] graph) {

        this.graph = graph;
        this.vertices = graph.length;
    }

    /*
     * 2-Approximation Algorithm:
     *
     * 1. Select an uncovered edge (u, v).
     * 2. Add both u and v to the vertex cover.
     * 3. Remove all edges incident to u or v.
     * 4. Repeat until all edges are covered.
     *
     * The resulting solution is at most
     * 2 times the size of an optimal
     * vertex cover.
     */

    public List<Integer> approximateVertexCover() {

        boolean[][] remaining =
                new boolean[vertices][vertices];

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = 0;
                 j < vertices;
                 j++) {

                remaining[i][j] =
                        graph[i][j] == 1;
            }
        }

        boolean[] selected =
                new boolean[vertices];

        List<Integer> vertexCover =
                new ArrayList<>();

        while (true) {

            int u = -1;
            int v = -1;

            // Find an uncovered edge
            for (int i = 0;
                 i < vertices;
                 i++) {

                for (int j = i + 1;
                     j < vertices;
                     j++) {

                    if (remaining[i][j]) {

                        u = i;
                        v = j;

                        break;
                    }
                }

                if (u != -1) {
                    break;
                }
            }

            // No edges remain
            if (u == -1) {
                break;
            }

            // Select both endpoints
            if (!selected[u]) {

                selected[u] = true;
                vertexCover.add(u);
            }

            if (!selected[v]) {

                selected[v] = true;
                vertexCover.add(v);
            }

            // Remove all edges incident
            // to u or v
            for (int i = 0;
                 i < vertices;
                 i++) {

                remaining[u][i] = false;
                remaining[i][u] = false;

                remaining[v][i] = false;
                remaining[i][v] = false;
            }
        }

        return vertexCover;
    }

    public void printVertexCover(
            List<Integer> vertexCover) {

        System.out.println(
                "========== VERTEX COVER 2-APPROXIMATION =========="
        );

        System.out.println(
                "Selected Vertices:"
        );

        for (int vertex : vertexCover) {

            System.out.println(
                    "Vertex " + vertex
            );
        }

        System.out.println();

        System.out.println(
                "Approximate Vertex Cover Size: "
                + vertexCover.size()
        );

        System.out.println(
                "Approximation Bound: "
                + "At most 2 × OPT"
        );
    }
}