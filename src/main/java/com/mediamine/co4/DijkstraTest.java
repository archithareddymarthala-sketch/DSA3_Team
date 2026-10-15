package com.mediamine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

import com.mediamine.database.MediaDAO;
import com.mediamine.model.Media;

import com.mediamine.co1.AhoCorasick;

import com.mediamine.co3.QueryCorrection;
import com.mediamine.co3.NeedlemanWunsch;
import com.mediamine.co3.SmithWaterman;

import com.mediamine.co4.FordFulkerson;
import com.mediamine.co4.EdmondsKarp;
import com.mediamine.co4.Dijkstra;
import com.mediamine.co4.BipartiteMatching;
import com.mediamine.co4.MinCut;

import com.mediamine.co5.ThreeSAT;
import com.mediamine.co5.ThreeSATToClique;
import com.mediamine.co5.CliqueToIndependentSet;
import com.mediamine.co5.IndependentSetToVertexCover;
import com.mediamine.co5.VertexCoverApproximation;

import com.mediamine.co6.RandomizedQuickSort;
import com.mediamine.co6.ReservoirSampling;
import com.mediamine.co6.MillerRabin;
import com.mediamine.co6.BlellochScan;
import com.mediamine.co6.ParallelReduce;
import com.mediamine.co6.BrentsTheorem;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MediaDAO mediaDAO = new MediaDAO();

        int choice;

        do {

            System.out.println("\n==============================================");
            System.out.println("             MEDIAMINE PLATFORM");
            System.out.println("==============================================");

            System.out.println("1. Add Media");
            System.out.println("2. View All Media");
            System.out.println("3. Search Media");
            System.out.println("4. Delete Media");
            System.out.println("5. Query Correction");
            System.out.println("6. Sequence Alignment");
            System.out.println("7. Network Flow Algorithms");
            System.out.println("8. NP-Completeness & Approximation");
            System.out.println("9. Randomized & Parallel Algorithms");
            System.out.println("10. Exit");

            System.out.println("==============================================");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // =========================================================
                // 1. ADD MEDIA
                // =========================================================

                case 1:

                    System.out.println(
                            "\n========== ADD MEDIA =========="
                    );

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter media type: ");
                    String mediaType = scanner.nextLine();

                    System.out.print("Enter content: ");
                    String content = scanner.nextLine();

                    Media media = new Media(
                            title,
                            category,
                            mediaType,
                            content
                    );

                    mediaDAO.addMedia(media);

                    break;


                // =========================================================
                // 2. VIEW ALL MEDIA
                // =========================================================

                case 2:

                    System.out.println(
                            "\n========== ALL MEDIA =========="
                    );

                    List<Media> mediaList =
                            mediaDAO.getAllMedia();

                    if (mediaList.isEmpty()) {

                        System.out.println(
                                "No media found."
                        );

                    } else {

                        for (Media item : mediaList) {

                            System.out.println(
                                    "----------------------------"
                            );

                            System.out.println(item);
                        }
                    }

                    break;


                // =========================================================
                // 3. SEARCH MEDIA
                // =========================================================

                case 3:

                    System.out.print(
                            "\nEnter search keyword: "
                    );

                    String keyword =
                            scanner.nextLine();

                    System.out.println(
                            "\nSelect Search Algorithm:"
                    );

                    System.out.println("1. KMP");
                    System.out.println("2. Z-Function");
                    System.out.println("3. Rabin-Karp");
                    System.out.println("4. Suffix Array");
                    System.out.println("5. Aho-Corasick");

                    System.out.print(
                            "Enter algorithm: "
                    );

                    int algorithm =
                            scanner.nextInt();

                    scanner.nextLine();

                    if (algorithm < 1 ||
                        algorithm > 5) {

                        System.out.println(
                                "Invalid algorithm choice."
                        );

                        break;
                    }


                    // -----------------------------------------------------
                    // KMP / Z / RABIN-KARP / SUFFIX ARRAY
                    // -----------------------------------------------------

                    if (algorithm >= 1 &&
                        algorithm <= 4) {

                        List<Media> searchResults;

                        if (algorithm == 4) {

                            searchResults =
                                    mediaDAO
                                    .searchMediaUsingSuffixArray(
                                            keyword
                                    );

                        } else {

                            searchResults =
                                    mediaDAO.searchMedia(
                                            keyword,
                                            algorithm
                                    );
                        }

                        System.out.println(
                                "\n========== SEARCH RESULTS =========="
                        );

                        if (searchResults.isEmpty()) {

                            System.out.println(
                                    "No matching media found."
                            );

                        } else {

                            for (Media item :
                                    searchResults) {

                                System.out.println(
                                        "----------------------------"
                                );

                                System.out.println(item);
                            }
                        }
                    }


                    // -----------------------------------------------------
                    // AHO-CORASICK
                    // -----------------------------------------------------

                    else {

                        System.out.println(
                                "\n========== AHO-CORASICK SEARCH =========="
                        );

                        System.out.println(
                                "Enter multiple keywords separated by commas."
                        );

                        System.out.println(
                                "Example: java, machine, technology"
                        );

                        System.out.print(
                                "Keywords: "
                        );

                        String keywordInput =
                                scanner.nextLine();

                        String[] keywords =
                                keywordInput.split(",");


                        AhoCorasick ahoCorasick =
                                new AhoCorasick();


                        // Add keywords

                        for (String word : keywords) {

                            String cleanedWord =
                                    word.trim();

                            if (!cleanedWord.isEmpty()) {

                                ahoCorasick.addKeyword(
                                        cleanedWord
                                );
                            }
                        }


                        // Build failure links

                        ahoCorasick.build();


                        // Get all media

                        List<Media> allMedia =
                                mediaDAO.getAllMedia();

                        boolean anyMediaFound = false;


                        for (Media item : allMedia) {

                            String text =
                                    item.getTitle()
                                    + " "
                                    + item.getContent();

                            List<String> foundKeywords =
                                    ahoCorasick.search(text);


                            if (!foundKeywords.isEmpty()) {

                                anyMediaFound = true;

                                System.out.println(
                                        "\n----------------------------"
                                );

                                System.out.println(
                                        "Media ID: "
                                        + item.getMediaId()
                                );

                                System.out.println(
                                        "Title: "
                                        + item.getTitle()
                                );

                                System.out.println(
                                        "Category: "
                                        + item.getCategory()
                                );

                                System.out.println(
                                        "Media Type: "
                                        + item.getMediaType()
                                );

                                System.out.println(
                                        "Matched Keywords: "
                                        + foundKeywords
                                );
                            }
                        }


                        if (!anyMediaFound) {

                            System.out.println(
                                    "No matching media found."
                            );
                        }
                    }

                    break;


                // =========================================================
                // 4. DELETE MEDIA
                // =========================================================

                case 4:

                    System.out.print(
                            "Enter Media ID to delete: "
                    );

                    int mediaId =
                            scanner.nextInt();

                    scanner.nextLine();

                    mediaDAO.deleteMedia(mediaId);

                    break;


                // =========================================================
                // 5. QUERY CORRECTION
                // =========================================================

                case 5:

                    System.out.println(
                            "\n========== QUERY CORRECTION =========="
                    );

                    System.out.print(
                            "Enter search query: "
                    );

                    String query =
                            scanner.nextLine();


                    String[] knownTerms = {

                            "artificial",
                            "intelligence",
                            "technology",
                            "science",
                            "data",
                            "climate",
                            "change",
                            "news",
                            "modern",
                            "computer"
                    };


                    String suggestion =
                            QueryCorrection
                            .suggestCorrection(
                                    query,
                                    knownTerms
                            );


                    if (suggestion.equalsIgnoreCase(
                            query)) {

                        System.out.println(
                                "No correction needed."
                        );

                    } else {

                        System.out.println(
                                "Suggested Correction: "
                                + suggestion
                        );
                    }

                    break;


                // =========================================================
                // 6. SEQUENCE ALIGNMENT
                // =========================================================

                case 6:

                    sequenceAlignmentMenu(scanner);

                    break;


                // =========================================================
                // 7. NETWORK FLOW ALGORITHMS - CO4
                // =========================================================

                case 7:

                    networkFlowMenu();

                    break;


                // =========================================================
                // 8. NP-COMPLETENESS & APPROXIMATION - CO5
                // =========================================================

                case 8:

                    npCompletenessMenu();

                    break;


                // =========================================================
                // 9. RANDOMIZED & PARALLEL ALGORITHMS - CO6
                // =========================================================

                case 9:

                    randomizedParallelMenu();

                    break;


                // =========================================================
                // 10. EXIT
                // =========================================================

                case 10:

                    System.out.println(
                            "\nExiting MediaMine..."
                    );

                    System.out.println(
                            "Thank you!"
                    );

                    break;


                default:

                    System.out.println(
                            "Invalid choice. Try again."
                    );
            }

        } while (choice != 10);

        scanner.close();
    }


    // =====================================================================
    // SEQUENCE ALIGNMENT MENU
    // =====================================================================

    private static void sequenceAlignmentMenu(
            Scanner scanner) {

        System.out.println(
                "\n========== SEQUENCE ALIGNMENT =========="
        );

        System.out.println(
                "1. Needleman-Wunsch"
        );

        System.out.println(
                "2. Smith-Waterman"
        );

        System.out.println(
                "3. Back"
        );

        System.out.print(
                "Enter algorithm: "
        );

        int alignmentChoice =
                scanner.nextInt();

        scanner.nextLine();


        if (alignmentChoice == 3) {
            return;
        }


        if (alignmentChoice < 1 ||
            alignmentChoice > 2) {

            System.out.println(
                    "Invalid algorithm choice."
            );

            return;
        }


        System.out.print(
                "Enter first sequence/text: "
        );

        String sequence1 =
                scanner.nextLine();


        System.out.print(
                "Enter second sequence/text: "
        );

        String sequence2 =
                scanner.nextLine();


        int alignmentScore;


        if (alignmentChoice == 1) {

            alignmentScore =
                    NeedlemanWunsch
                    .calculateScore(
                            sequence1,
                            sequence2
                    );


            System.out.println(
                    "\n========== NEEDLEMAN-WUNSCH =========="
            );

            System.out.println(
                    "Sequence 1: "
                    + sequence1
            );

            System.out.println(
                    "Sequence 2: "
                    + sequence2
            );

            System.out.println(
                    "Global Alignment Score: "
                    + alignmentScore
            );

        } else {

            alignmentScore =
                    SmithWaterman
                    .calculateScore(
                            sequence1,
                            sequence2
                    );


            System.out.println(
                    "\n========== SMITH-WATERMAN =========="
            );

            System.out.println(
                    "Sequence 1: "
                    + sequence1
            );

            System.out.println(
                    "Sequence 2: "
                    + sequence2
            );

            System.out.println(
                    "Local Alignment Score: "
                    + alignmentScore
            );
        }
    }


    // =====================================================================
    // CO4 - NETWORK FLOW ALGORITHMS
    // =====================================================================

    private static void networkFlowMenu() {

        Scanner scanner =
                new Scanner(System.in);

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "       CO4 - NETWORK FLOW ALGORITHMS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Ford-Fulkerson");
        System.out.println("2. Edmonds-Karp");
        System.out.println("3. Dijkstra");
        System.out.println("4. Bipartite Matching");
        System.out.println("5. Min-Cut");
        System.out.println("6. Back");

        System.out.print(
                "Enter choice: "
        );

        int choice =
                scanner.nextInt();


        if (choice == 6) {
            return;
        }


        // ================================================================
        // COMMON FLOW GRAPH
        // ================================================================

        int[][] graph = {

                {0, 16, 13, 0, 0, 0},

                {0, 0, 10, 12, 0, 0},

                {0, 4, 0, 0, 14, 0},

                {0, 0, 9, 0, 0, 20},

                {0, 0, 0, 7, 0, 4},

                {0, 0, 0, 0, 0, 0}
        };

        int vertices = 6;

        int source = 0;

        int sink = 5;


        // ================================================================
        // 1. FORD-FULKERSON
        // ================================================================

        if (choice == 1) {

            FordFulkerson fordFulkerson =
                    new FordFulkerson(vertices);

            int maxFlow =
                    fordFulkerson.maxFlow(
                            graph,
                            source,
                            sink
                    );

            System.out.println(
                    "\n========== FORD-FULKERSON =========="
            );

            System.out.println(
                    "Source: 0"
            );

            System.out.println(
                    "Sink: 5"
            );

            System.out.println(
                    "Maximum Flow: "
                    + maxFlow
            );
        }


        // ================================================================
        // 2. EDMONDS-KARP
        // ================================================================

        else if (choice == 2) {

            EdmondsKarp edmondsKarp =
                    new EdmondsKarp(vertices);

            int maxFlow =
                    edmondsKarp.maxFlow(
                            graph,
                            source,
                            sink
                    );

            System.out.println(
                    "\n========== EDMONDS-KARP =========="
            );

            System.out.println(
                    "Source: 0"
            );

            System.out.println(
                    "Sink: 5"
            );

            System.out.println(
                    "Maximum Flow: "
                    + maxFlow
            );
        }


        // ================================================================
        // 3. DIJKSTRA
        // ================================================================

        else if (choice == 3) {

            int[][] weightedGraph = {

                    {0, 4, 2, 0, 0, 0},

                    {4, 0, 1, 5, 0, 0},

                    {2, 1, 0, 8, 10, 0},

                    {0, 5, 8, 0, 2, 6},

                    {0, 0, 10, 2, 0, 3},

                    {0, 0, 0, 6, 3, 0}
            };


            Dijkstra dijkstra =
                    new Dijkstra(6);


            int[] distances =
                    dijkstra.shortestPath(
                            weightedGraph,
                            0
                    );


            System.out.println(
                    "\n========== DIJKSTRA =========="
            );

            System.out.println(
                    "Media Delivery Network"
            );

            System.out.println(
                    "0 = Media Server"
            );

            System.out.println(
                    "1 = News Server"
            );

            System.out.println(
                    "2 = Video Server"
            );

            System.out.println(
                    "3 = Regional Server"
            );

            System.out.println(
                    "4 = Channel Server"
            );

            System.out.println(
                    "5 = User"
            );

            System.out.println();


            // IMPORTANT:
            // Your actual Dijkstra class requires
            // TWO arguments here.

            dijkstra.printDistances(
                    distances,
                    0
            );
        }


        // ================================================================
        // 4. BIPARTITE MATCHING
        // ================================================================

        else if (choice == 4) {

            String[] mediaNames = {

                    "News Article",

                    "AI Article",

                    "Technology Video",

                    "Podcast"
            };


            String[] channelNames = {

                    "News Channel",

                    "Technology Channel",

                    "Video Channel",

                    "Audio Channel"
            };


            int[][] compatibility = {

                    {1, 0, 0, 0},

                    {0, 1, 1, 0},

                    {0, 1, 1, 0},

                    {0, 0, 0, 1}
            };


            BipartiteMatching matching =
                    new BipartiteMatching(
                            mediaNames.length,
                            channelNames.length
                    );


            int maximumMatching =
                    matching.maximumMatching(
                            compatibility
                    );


            System.out.println(
                    "\n========== BIPARTITE MATCHING =========="
            );

            System.out.println(
                    "Maximum Media-Channel Matching: "
                    + maximumMatching
            );


            // IMPORTANT:
            // Your actual method requires THREE arguments.

            matching.printMatching(
                    compatibility,
                    mediaNames,
                    channelNames
            );
        }


        // ================================================================
        // 5. MIN-CUT
        // ================================================================

        else if (choice == 5) {

            MinCut minCut =
                    new MinCut(vertices);


            // IMPORTANT:
            // Your actual method is calculateMinCut(),
            // not findMinCut().

            int minCutResult =
                    minCut.calculateMinCut(
                            graph,
                            source,
                            sink
                    );


            System.out.println(
                    "\n========== MIN-CUT =========="
            );

            System.out.println(
                    "Source: Media Source"
            );

            System.out.println(
                    "Sink: Users"
            );

            System.out.println(
                    "Minimum Cut Capacity: "
                    + minCutResult
            );
        }


        else {

            System.out.println(
                    "Invalid choice."
            );
        }
    }


    // =====================================================================
    // CO5 - NP-COMPLETENESS & APPROXIMATION
    // =====================================================================

    private static void npCompletenessMenu() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "   CO5 - NP-COMPLETENESS & APPROXIMATION"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "1. 3-SAT"
        );

        System.out.println(
                "2. 3-SAT -> CLIQUE"
        );

        System.out.println(
                "3. CLIQUE -> Independent Set"
        );

        System.out.println(
                "4. Independent Set -> Vertex Cover"
        );

        System.out.println(
                "5. Vertex Cover 2-Approximation"
        );

        System.out.println(
                "6. Back"
        );

        System.out.print(
                "Enter choice: "
        );


        Scanner scanner =
                new Scanner(System.in);

        int choice =
                scanner.nextInt();


        if (choice == 6) {
            return;
        }


        // ================================================================
        // COMMON 3-SAT FORMULA
        // ================================================================

        int[][] clauses = {

                {1, 2, 3},

                {-1, 2, 3},

                {1, -2, 3}
        };


        // ================================================================
        // 1. 3-SAT
        // ================================================================

        if (choice == 1) {

            ThreeSAT threeSAT =
                    new ThreeSAT(
                            clauses
                    );


            boolean result =
                    threeSAT.solve();


            System.out.println(
                    "\n========== 3-SAT =========="
            );

            System.out.println(
                    "Formula:"
            );

            System.out.println(
                    "(x1 OR x2 OR x3)"
            );

            System.out.println(
                    "(NOT x1 OR x2 OR x3)"
            );

            System.out.println(
                    "(x1 OR NOT x2 OR x3)"
            );

            System.out.println();

            System.out.println(
                    "Satisfiable: "
                    + result
            );
        }


        // ================================================================
        // 2. 3-SAT -> CLIQUE
        // ================================================================

        else if (choice == 2) {

            ThreeSATToClique conversion =
                    new ThreeSATToClique(
                            clauses
                    );


            int targetCliqueSize =
                    clauses.length;


            boolean result =
                    conversion.hasClique(
                            targetCliqueSize
                    );


            System.out.println(
                    "\n========== 3-SAT -> CLIQUE =========="
            );

            System.out.println(
                    "Number of Clauses: "
                    + clauses.length
            );

            System.out.println(
                    "Target Clique Size: "
                    + targetCliqueSize
            );

            System.out.println(
                    "Clique Exists: "
                    + result
            );
        }


        // ================================================================
        // 3. CLIQUE -> INDEPENDENT SET
        // ================================================================

        else if (choice == 3) {

            // Example graph

            int[][] graph = {

                    {0, 1, 1, 0},

                    {1, 0, 1, 1},

                    {1, 1, 0, 1},

                    {0, 1, 1, 0}
            };


            CliqueToIndependentSet conversion =
                    new CliqueToIndependentSet(
                            graph
                    );


            int targetSize = 2;


            boolean result =
                    conversion.hasIndependentSet(
                            targetSize
                    );


            System.out.println(
                    "\n========== CLIQUE -> INDEPENDENT SET =========="
            );

            System.out.println(
                    "Target Independent Set Size: "
                    + targetSize
            );

            System.out.println(
                    "Independent Set Exists: "
                    + result
            );
        }


        // ================================================================
        // 4. INDEPENDENT SET -> VERTEX COVER
        // ================================================================

        else if (choice == 4) {

            int vertices = 4;

            int independentSetSize = 2;


            IndependentSetToVertexCover conversion =
                    new IndependentSetToVertexCover(
                            vertices
                    );


            int vertexCoverSize =
                    conversion.calculateVertexCoverSize(
                            independentSetSize
                    );


            System.out.println(
                    "\n========== INDEPENDENT SET -> VERTEX COVER =========="
            );

            System.out.println(
                    "Number of Vertices: "
                    + vertices
            );

            System.out.println(
                    "Independent Set Size: "
                    + independentSetSize
            );

            System.out.println(
                    "Corresponding Vertex Cover Size: "
                    + vertexCoverSize
            );
        }


        // ================================================================
        // 5. VERTEX COVER 2-APPROXIMATION
        // ================================================================

        else if (choice == 5) {

            int vertices = 6;


            int[][] edges = {

                    {0, 1},

                    {0, 2},

                    {1, 3},

                    {2, 3},

                    {3, 4},

                    {4, 5}
            };


            VertexCoverApproximation approximation =
                    new VertexCoverApproximation(
                            vertices,
                            edges
                    );


            List<Integer> cover =
                    approximation.approximateVertexCover();


            System.out.println(
                    "\n========== VERTEX COVER 2-APPROXIMATION =========="
            );

            System.out.println(
                    "Approximate Vertex Cover: "
                    + cover
            );

            System.out.println(
                    "Approximate Cover Size: "
                    + cover.size()
            );

            System.out.println(
                    "Approximation Guarantee: "
                    + "At most 2 x OPT"
            );
        }


        else {

            System.out.println(
                    "Invalid choice."
            );
        }
    }


    // =====================================================================
    // CO6 - RANDOMIZED & PARALLEL ALGORITHMS
    // =====================================================================

    private static void randomizedParallelMenu() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "   CO6 - RANDOMIZED & PARALLEL ALGORITHMS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "1. Randomized QuickSort"
        );

        System.out.println(
                "2. Reservoir Sampling"
        );

        System.out.println(
                "3. Miller-Rabin"
        );

        System.out.println(
                "4. Blelloch Scan"
        );

        System.out.println(
                "5. Parallel Reduce"
        );

        System.out.println(
                "6. Brent's Theorem"
        );

        System.out.println(
                "7. Back"
        );

        System.out.print(
                "Enter choice: "
        );


        Scanner scanner =
                new Scanner(System.in);

        int choice =
                scanner.nextInt();


        if (choice == 7) {
            return;
        }


        // ================================================================
        // 1. RANDOMIZED QUICKSORT
        // ================================================================

        if (choice == 1) {

            int[] array = {

                    45,
                    12,
                    78,
                    34,
                    90,
                    23,
                    56,
                    10
            };


            RandomizedQuickSort quickSort =
                    new RandomizedQuickSort();


            System.out.println(
                    "\n========== RANDOMIZED QUICKSORT =========="
            );

            System.out.println(
                    "Before Sorting: "
                    + Arrays.toString(array)
            );


            quickSort.sort(array);


            System.out.println(
                    "After Sorting: "
                    + Arrays.toString(array)
            );
        }


        // ================================================================
        // 2. RESERVOIR SAMPLING
        // ================================================================

        else if (choice == 2) {

            int[] data = {

                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10
            };


            int k = 3;


            ReservoirSampling sampling =
                    new ReservoirSampling();


            int[] sample =
                    sampling.sample(
                            data,
                            k
                    );


            System.out.println(
                    "\n========== RESERVOIR SAMPLING =========="
            );

            System.out.println(
                    "Original Data: "
                    + Arrays.toString(data)
            );

            System.out.println(
                    "Sample Size: "
                    + k
            );

            System.out.println(
                    "Random Sample: "
                    + Arrays.toString(sample)
            );
        }


        // ================================================================
        // 3. MILLER-RABIN
        // ================================================================

        else if (choice == 3) {

            MillerRabin millerRabin =
                    new MillerRabin();


            int iterations = 5;


            long[] numbers = {

                    17,
                    25,
                    97,
                    100
            };


            System.out.println(
                    "\n========== MILLER-RABIN =========="
            );


            for (long number : numbers) {

                boolean prime =
                        millerRabin.isPrime(
                                number,
                                iterations
                        );


                System.out.println(
                        number
                        + " -> "
                        + (prime
                                ? "Probably Prime"
                                : "Composite")
                );
            }
        }


        // ================================================================
        // 4. BLELLOCH SCAN
        // ================================================================

        else if (choice == 4) {

            int[] input = {

                    1,
                    2,
                    3,
                    4
            };


            BlellochScan scan =
                    new BlellochScan();


            int[] result =
                    scan.exclusiveScan(
                            input
                    );


            System.out.println(
                    "\n========== BLELLOCH SCAN =========="
            );

            System.out.println(
                    "Input: "
                    + Arrays.toString(input)
            );

            System.out.println(
                    "Exclusive Prefix Sum: "
                    + Arrays.toString(result)
            );
        }


        // ================================================================
        // 5. PARALLEL REDUCE
        // ================================================================

        else if (choice == 5) {

            int[] array = {

                    10,
                    20,
                    30,
                    40,
                    50,
                    60
            };


            int threadCount = 3;


            ParallelReduce reduce =
                    new ParallelReduce();


            int result =
                    reduce.sum(
                            array,
                            threadCount
                    );


            System.out.println(
                    "\n========== PARALLEL REDUCE =========="
            );

            System.out.println(
                    "Input: "
                    + Arrays.toString(array)
            );

            System.out.println(
                    "Threads: "
                    + threadCount
            );

            System.out.println(
                    "Parallel Sum: "
                    + result
            );
        }


        // ================================================================
        // 6. BRENT'S THEOREM
        // ================================================================

        else if (choice == 6) {

            int totalWork = 100;

            int criticalPath = 10;

            int processors = 4;


            BrentsTheorem brent =
                    new BrentsTheorem();


            double bound =
                    brent.calculateBound(
                            totalWork,
                            criticalPath,
                            processors
                    );


            System.out.println(
                    "\n========== BRENT'S THEOREM =========="
            );

            System.out.println(
                    "Total Work (T1): "
                    + totalWork
            );

            System.out.println(
                    "Critical Path (T-infinity): "
                    + criticalPath
            );

            System.out.println(
                    "Processors (p): "
                    + processors
            );

            System.out.println(
                    "Formula: Tp <= T1 / p + T-infinity"
            );

            System.out.println(
                    "Upper Bound: "
                    + bound
            );
        }


        else {

            System.out.println(
                    "Invalid choice."
            );
        }
    }
}