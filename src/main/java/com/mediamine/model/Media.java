package com.mediamine.model;

public class Media {

    private int mediaId;
    private String title;
    private String category;
    private String mediaType;
    private String content;

    // Default constructor
    public Media() {
    }

    // Constructor without mediaId
    public Media(String title, String category,
                 String mediaType, String content) {

        this.title = title;
        this.category = category;
        this.mediaType = mediaType;
        this.content = content;
    }

    // Constructor with mediaId
    public Media(int mediaId, String title, String category,
                 String mediaType, String content) {

        this.mediaId = mediaId;
        this.title = title;
        this.category = category;
        this.mediaType = mediaType;
        this.content = content;
    }

    // Getters and Setters

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {

        return "Media ID: " + mediaId
                + "\nTitle: " + title
                + "\nCategory: " + category
                + "\nMedia Type: " + mediaType
                + "\nContent: " + content;
    }
}