package com.mediamine.co4;

import java.util.*;

public class MinCut {

    private int vertices;

    public MinCut(int vertices) {
        this.vertices = vertices;
    }

    // BFS to find an augmenting path
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

    // Calculate maximum flow
    private int calculateMaxFlow(
            int[][] graph,
            int source,
            int sink,
            int[][] residualGraph) {

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

        while (bfs(
                residualGraph,
                source,
                sink,
                parent)) {

            int pathFlow =
                    Integer.MAX_VALUE;

            int current = sink;

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

    // Find the vertices reachable from source
    private void findReachableVertices(
            int[][] residualGraph,
            int source,
            boolean[] visited) {

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(source);

        visited[source] = true;

        while (!queue.isEmpty()) {

            int current =
                    queue.poll();

            for (int next = 0;
                 next < vertices;
                 next++) {

                if (!visited[next]
                        && residualGraph[current][next] > 0) {

                    visited[next] = true;

                    queue.add(next);
                }
            }
        }
    }

    // Calculate minimum cut
    public int calculateMinCut(
            int[][] graph,
            int source,
            int sink) {

        int[][] residualGraph =
                new int[vertices][vertices];

        int maxFlow =
                calculateMaxFlow(
                        graph,
                        source,
                        sink,
                        residualGraph
                );

        boolean[] reachable =
                new boolean[vertices];

        findReachableVertices(
                residualGraph,
                source,
                reachable
        );

        int minCut = 0;

        System.out.println(
                "\nMinimum Cut Edges:"
        );

        for (int i = 0;
             i < vertices;
             i++) {

            for (int j = 0;
                 j < vertices;
                 j++) {

                if (reachable[i]
                        && !reachable[j]
                        && graph[i][j] > 0) {

                    System.out.println(
                            i
                            + " -> "
                            + j
                            + " : Capacity = "
                            + graph[i][j]
                    );

                    minCut += graph[i][j];
                }
            }
        }

        System.out.println(
                "\nMaximum Flow: "
                + maxFlow
        );

        return minCut;
    }
}