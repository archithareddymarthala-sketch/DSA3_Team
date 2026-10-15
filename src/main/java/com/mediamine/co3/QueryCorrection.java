package com.mediamine.co3;

public class QueryCorrection {

    public static String suggestCorrection(
            String query,
            String[] knownTerms) {

        String bestMatch = query;
        int bestDistance = Integer.MAX_VALUE;

        for (String term : knownTerms) {

            int distance =
                    EditDistance.calculate(
                            query.toLowerCase(),
                            term.toLowerCase()
                    );

            if (distance < bestDistance) {
                bestDistance = distance;
                bestMatch = term;
            }
        }

        return bestMatch;
    }
}