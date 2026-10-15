package com.mediamine.co6;

public class MillerRabinTest {

    public static void main(String[] args) {

        MillerRabin millerRabin =
                new MillerRabin();

        int iterations = 10;

        long[] numbers = {
                17,
                25,
                97,
                100
        };

        System.out.println(
                "========== MILLER-RABIN =========="
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

        System.out.println();

        System.out.println(
                "Result: Miller-Rabin primality testing completed."
        );

        System.out.println(
                "Randomized primality testing was demonstrated."
        );
    }
}