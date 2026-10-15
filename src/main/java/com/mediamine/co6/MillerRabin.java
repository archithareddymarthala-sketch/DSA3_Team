package com.mediamine.co6;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    private Random random =
            new Random();

    /*
     * Miller-Rabin probabilistic
     * primality test.
     */
    public boolean isPrime(
            long number,
            int iterations) {

        if (number < 2) {
            return false;
        }

        if (number == 2 ||
            number == 3) {

            return true;
        }

        if (number % 2 == 0) {
            return false;
        }

        long d = number - 1;

        int r = 0;

        while (d % 2 == 0) {

            d = d / 2;
            r++;
        }

        for (int i = 0;
             i < iterations;
             i++) {

            long a =
                    2 + random.nextInt(
                            (int) Math.min(
                                    number - 3,
                                    Integer.MAX_VALUE
                            )
                    );

            long x =
                    modPower(
                            a,
                            d,
                            number
                    );

            if (x == 1 ||
                x == number - 1) {

                continue;
            }

            boolean probablyPrime = false;

            for (int j = 1;
                 j < r;
                 j++) {

                x =
                        modMultiply(
                                x,
                                x,
                                number
                        );

                if (x == number - 1) {

                    probablyPrime = true;
                    break;
                }
            }

            if (!probablyPrime) {

                return false;
            }
        }

        return true;
    }

    private long modPower(
            long base,
            long exponent,
            long modulus) {

        long result = 1;

        base =
                base % modulus;

        while (exponent > 0) {

            if ((exponent & 1) == 1) {

                result =
                        modMultiply(
                                result,
                                base,
                                modulus
                        );
            }

            base =
                    modMultiply(
                            base,
                            base,
                            modulus
                    );

            exponent =
                    exponent >> 1;
        }

        return result;
    }

    private long modMultiply(
            long a,
            long b,
            long modulus) {

        return BigInteger.valueOf(a)
                .multiply(
                        BigInteger.valueOf(b)
                )
                .mod(
                        BigInteger.valueOf(modulus)
                )
                .longValue();
    }
}