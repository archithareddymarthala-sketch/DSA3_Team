package com.mediamine.co4;

import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

    private int vertices;

    public EdmondsKarp(int vertices) {
        this.vertices = vertices;
    }

    // BFS finds the shortest augmenting path
    private boolean bfs(
            int[][] residualGraph,
            int source,
            int sink,
            int[] parent) {

        boolean[] visited =
                new boolean[vertices];

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(source);

        visited[source] = true;

        parent[source] = -1;

        while (!queue.isEmpty()) {

            int current =
                    queue.poll();

            for (int next = 0;
                 next < vertices;
                 next++) {

                if (!visited[next]
                        && residualGraph[current][next] > 0) {

                    queue.add(next);

                    parent[next] =
                            current;

                    visited[next] = true;
                }
            }
        }

        return visited[sink];
    }

    // Edmonds-Karp algorithm
    public int maxFlow(
            int[][] graph,
            int source,
            int sink) {

        int[][] residualGraph =
                new int[vertices][vertices];

        // Copy original graph
        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = 0;
                 j < vertices;
                 j++) {

                residualGraph[i][j] =
                        graph[i][j];
            }
        }

        int[] parent =
                new int[vertices];

        int maxFlow = 0;

        // Repeatedly find shortest augmenting path
        while (bfs(
                residualGraph,
                source,
                sink,
                parent)) {

            int pathFlow =
                    Integer.MAX_VALUE;

            int current = sink;

            // Find minimum capacity in path
            while (current != source) {

                int previous =
                        parent[current];

                pathFlow =
                        Math.min(
                                pathFlow,
                                residualGraph[
                                        previous
                                ][current]
                        );

                current = previous;
            }

            // Update residual capacities
            current = sink;

            while (current != source) {

                int previous =
                        parent[current];

                residualGraph[
                        previous
                ][current] -= pathFlow;

                residualGraph[
                        current
                ][previous] += pathFlow;

                current = previous;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    // Display the network
    public void printGraph(int[][] graph) {

        System.out.println(
                "Media Distribution Network:"
        );

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = 0;
                 j < vertices;
                 j++) {

                if (graph[i][j] > 0) {

                    System.out.println(
                            i
                            + " -> "
                            + j
                            + " : Capacity = "
                            + graph[i][j]
                    );
                }
            }
        }
    }
}