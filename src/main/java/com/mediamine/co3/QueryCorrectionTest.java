package com.mediamine.co3;

public class QueryCorrectionTest {

    public static void main(String[] args) {

        String query = "artifical";

        String[] knownTerms = {
            "artificial",
            "intelligence",
            "technology",
            "science",
            "data"
        };

        String suggestion =
                QueryCorrection.suggestCorrection(
                        query,
                        knownTerms
                );

        System.out.println("User Query: " + query);
        System.out.println("Suggested Correction: " + suggestion);
    }
}