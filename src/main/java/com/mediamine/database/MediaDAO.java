package com.mediamine.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mediamine.model.Media;
import com.mediamine.co2.SuffixArray;

public class MediaDAO {

    // Add a new media record
    public void addMedia(Media media) {

        String sql = "INSERT INTO media "
                   + "(title, category, media_type, content) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, media.getTitle());
            statement.setString(2, media.getCategory());
            statement.setString(3, media.getMediaType());
            statement.setString(4, media.getContent());

            statement.executeUpdate();

            System.out.println("Media added successfully!");

        } catch (Exception e) {

            System.out.println("Error adding media.");
            e.printStackTrace();
        }
    }

    // Get all media records
    public List<Media> getAllMedia() {

        List<Media> mediaList = new ArrayList<>();

        String sql = "SELECT * FROM media";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Media media = new Media();

                media.setMediaId(
                        resultSet.getInt("media_id"));

                media.setTitle(
                        resultSet.getString("title"));

                media.setCategory(
                        resultSet.getString("category"));

                media.setMediaType(
                        resultSet.getString("media_type"));

                media.setContent(
                        resultSet.getString("content"));

                mediaList.add(media);
            }

        } catch (Exception e) {

            System.out.println("Error retrieving media.");
            e.printStackTrace();
        }

        return mediaList;
    }

    // Get media by ID
    public Media getMediaById(int mediaId) {

        String sql =
                "SELECT * FROM media WHERE media_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, mediaId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Media media = new Media();

                media.setMediaId(
                        resultSet.getInt("media_id"));

                media.setTitle(
                        resultSet.getString("title"));

                media.setCategory(
                        resultSet.getString("category"));

                media.setMediaType(
                        resultSet.getString("media_type"));

                media.setContent(
                        resultSet.getString("content"));

                return media;
            }

        } catch (Exception e) {

            System.out.println("Error finding media.");
            e.printStackTrace();
        }

        return null;
    }

    // Delete media
    public void deleteMedia(int mediaId) {

        String sql =
                "DELETE FROM media WHERE media_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, mediaId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Media deleted successfully!");
            } else {
                System.out.println("Media not found.");
            }

        } catch (Exception e) {

            System.out.println("Error deleting media.");
            e.printStackTrace();
        }
    }

    // Search media using KMP, Z-Function, or Rabin-Karp
    public List<Media> searchMedia(
            String keyword,
            int algorithm) {

        List<Media> results = new ArrayList<>();

        String sql = "SELECT * FROM media";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Media media = new Media();

                media.setMediaId(
                        resultSet.getInt("media_id")
                );

                media.setTitle(
                        resultSet.getString("title")
                );

                media.setCategory(
                        resultSet.getString("category")
                );

                media.setMediaType(
                        resultSet.getString("media_type")
                );

                media.setContent(
                        resultSet.getString("content")
                );

                boolean found = false;

                String text =
                        media.getTitle() + " "
                        + media.getContent();

                if (algorithm == 1) {

                    found = com.mediamine.co1.KMP.search(
                            text,
                            keyword
                    );

                } else if (algorithm == 2) {

                    found = com.mediamine.co1.ZFunction.search(
                            text,
                            keyword
                    );

                } else if (algorithm == 3) {

                    found = com.mediamine.co1.RabinKarp.search(
                            text,
                            keyword
                    );
                }

                if (found) {
                    results.add(media);
                }
            }

        } catch (Exception e) {

            System.out.println("Error searching media.");
            e.printStackTrace();
        }

        return results;
    }

    // Search media using Suffix Array
    public List<Media> searchMediaUsingSuffixArray(
            String keyword) {

        List<Media> results = new ArrayList<>();

        String sql = "SELECT * FROM media";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Media media = new Media();

                media.setMediaId(
                        resultSet.getInt("media_id")
                );

                media.setTitle(
                        resultSet.getString("title")
                );

                media.setCategory(
                        resultSet.getString("category")
                );

                media.setMediaType(
                        resultSet.getString("media_type")
                );

                media.setContent(
                        resultSet.getString("content")
                );

                String text =
                        media.getTitle() + " "
                        + media.getContent();

                // Convert to lowercase for case-insensitive search
                String searchText =
                        text.toLowerCase();

                String searchKeyword =
                        keyword.toLowerCase();

                // Build suffix array
                int[] suffixArray =
                        SuffixArray.buildSuffixArray(
                                searchText
                        );

                boolean found =
                        containsUsingSuffixArray(
                                searchText,
                                searchKeyword,
                                suffixArray
                        );

                if (found) {
                    results.add(media);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error searching using Suffix Array."
            );

            e.printStackTrace();
        }

        return results;
    }

    // Check whether a pattern exists using a Suffix Array
    private boolean containsUsingSuffixArray(
            String text,
            String pattern,
            int[] suffixArray) {

        int left = 0;
        int right = suffixArray.length - 1;

        while (left <= right) {

            int mid =
                    left + (right - left) / 2;

            int suffixIndex =
                    suffixArray[mid];

            String suffix =
                    text.substring(suffixIndex);

            int comparison =
                    comparePrefix(
                            suffix,
                            pattern
                    );

            if (comparison == 0) {
                return true;
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    // Compare pattern with the beginning of a suffix
    private int comparePrefix(
            String suffix,
            String pattern) {

        int length =
                Math.min(
                        suffix.length(),
                        pattern.length()
                );

        for (int i = 0; i < length; i++) {

            char c1 = suffix.charAt(i);
            char c2 = pattern.charAt(i);

            if (c1 < c2) {
                return -1;
            }

            if (c1 > c2) {
                return 1;
            }
        }

        if (pattern.length() <= suffix.length()) {
            return 0;
        }

        return -1;
    }
}