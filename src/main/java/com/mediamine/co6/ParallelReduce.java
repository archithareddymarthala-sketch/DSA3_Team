package com.mediamine.co6;

public class ParallelReduce {

    /*
     * Performs reduction using
     * multiple worker threads.
     *
     * Here the operation is SUM.
     */

    public long sum(
            int[] array,
            int threadCount) {

        if (array.length == 0) {
            return 0;
        }

        if (threadCount <= 0) {

            throw new IllegalArgumentException(
                    "Thread count must be positive."
            );
        }

        threadCount =
                Math.min(
                        threadCount,
                        array.length
                );

        long[] partialSums =
                new long[threadCount];

        Thread[] threads =
                new Thread[threadCount];

        int chunkSize =
                (array.length + threadCount - 1)
                / threadCount;

        for (int t = 0;
             t < threadCount;
             t++) {

            final int threadIndex = t;

            final int start =
                    t * chunkSize;

            final int end =
                    Math.min(
                            start + chunkSize,
                            array.length
                    );

            threads[t] =
                    new Thread(() -> {

                        long localSum = 0;

                        for (int i = start;
                             i < end;
                             i++) {

                            localSum +=
                                    array[i];
                        }

                        partialSums[threadIndex] =
                                localSum;
                    });

            threads[t].start();
        }

        for (Thread thread : threads) {

            try {

                thread.join();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                throw new RuntimeException(
                        "Thread interrupted.",
                        e
                );
            }
        }

        long total = 0;

        for (long partial :
                partialSums) {

            total += partial;
        }

        return total;
    }
}