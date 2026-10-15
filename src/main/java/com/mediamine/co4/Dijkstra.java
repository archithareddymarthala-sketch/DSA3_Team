package com.mediamine.co4;

import java.util.Arrays;

public class Dijkstra {

    private int vertices;

    public Dijkstra(int vertices) {
        this.vertices = vertices;
    }

    // Find the vertex with minimum distance
    private int findMinimumVertex(
            int[] distance,
            boolean[] visited) {

        int minimum =
                Integer.MAX_VALUE;

        int minimumVertex = -1;

        for (int i = 0;
             i < vertices;
             i++) {

            if (!visited[i]
                    && distance[i] < minimum) {

                minimum =
                        distance[i];

                minimumVertex = i;
            }
        }

        return minimumVertex;
    }

    // Dijkstra's shortest path algorithm
    public int[] shortestPath(
            int[][] graph,
            int source) {

        int[] distance =
                new int[vertices];

        boolean[] visited =
                new boolean[vertices];

        Arrays.fill(
                distance,
                Integer.MAX_VALUE
        );

        distance[source] = 0;

        for (int count = 0;
             count < vertices - 1;
             count++) {

            int current =
                    findMinimumVertex(
                            distance,
                            visited
                    );

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (int next = 0;
                 next < vertices;
                 next++) {

                if (!visited[next]
                        && graph[current][next] > 0
                        && distance[current]
                        != Integer.MAX_VALUE
                        && distance[current]
                        + graph[current][next]
                        < distance[next]) {

                    distance[next] =
                            distance[current]
                            + graph[current][next];
                }
            }
        }

        return distance;
    }

    // Print shortest paths
    public void printDistances(
            int[] distance,
            int source) {

        System.out.println(
                "Shortest distances from node "
                + source
        );

        for (int i = 0;
             i < vertices;
             i++) {

            if (distance[i]
                    == Integer.MAX_VALUE) {

                System.out.println(
                        source
                        + " -> "
                        + i
                        + " : Unreachable"
                );

            } else {

                System.out.println(
                        source
                        + " -> "
                        + i
                        + " : "
                        + distance[i]
                );
            }
        }
    }
}