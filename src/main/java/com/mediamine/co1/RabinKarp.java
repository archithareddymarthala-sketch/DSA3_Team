package com.mediamine.co1;

public class RabinKarp {

    private static final int BASE = 256;
    private static final int PRIME = 101;

    public static boolean search(String text, String pattern) {

        if (pattern == null || pattern.isEmpty()) {
            return true;
        }

        if (text == null || text.isEmpty()) {
            return false;
        }

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            return false;
        }

        int patternHash = 0;
        int textHash = 0;
        int highestPower = 1;

        // Calculate BASE^(m-1)
        for (int i = 0; i < m - 1; i++) {
            highestPower =
                    (highestPower * BASE) % PRIME;
        }

        // Calculate initial hashes
        for (int i = 0; i < m; i++) {

            patternHash =
                    (BASE * patternHash
                    + pattern.charAt(i)) % PRIME;

            textHash =
                    (BASE * textHash
                    + text.charAt(i)) % PRIME;
        }

        // Slide the pattern over the text
        for (int i = 0; i <= n - m; i++) {

            // Hash values match
            if (patternHash == textHash) {

                boolean match = true;

                // Verify actual characters
                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j)
                            != pattern.charAt(j)) {

                        match = false;
                        break;
                    }
                }

                if (match) {
                    return true;
                }
            }

            // Calculate next window hash
            if (i < n - m) {

                textHash =
                        (BASE *
                        (textHash
                        - text.charAt(i) * highestPower)
                        + text.charAt(i + m))
                        % PRIME;

                if (textHash < 0) {
                    textHash += PRIME;
                }
            }
        }

        return false;
    }
}