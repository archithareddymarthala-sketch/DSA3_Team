package com.mediamine.co4;

public class FordFulkersonTest {

    public static void main(String[] args) {

        int vertices = 6;

        FordFulkerson fordFulkerson =
                new FordFulkerson(vertices);

        /*
         * MediaMine Distribution Network
         *
         * 0 = Media Source
         * 1 = News Server
         * 2 = Video Server
         * 3 = Regional Server
         * 4 = Distribution Server
         * 5 = Users
         */

        int[][] graph = {

                {0, 16, 13, 0, 0, 0},

                {0, 0, 10, 12, 0, 0},

                {0, 4, 0, 0, 14, 0},

                {0, 0, 9, 0, 0, 20},

                {0, 0, 0, 7, 0, 4},

                {0, 0, 0, 0, 0, 0}
        };

        System.out.println(
                "========== FORD-FULKERSON =========="
        );

        fordFulkerson.printGraph(graph);

        int source = 0;
        int sink = 5;

        int maxFlow =
                fordFulkerson.maxFlow(
                        graph,
                        source,
                        sink
                );

        System.out.println();

        System.out.println(
                "Source: Media Source"
        );

        System.out.println(
                "Sink: Users"
        );

        System.out.println(
                "Maximum Media Distribution Flow: "
                + maxFlow
        );
    }
}