package com.example.movieManagement.model;

public class Movie {
    private String title;
    private String rating;
    private String description;
    private String review;

    public Movie(String title, String rating, String description, String review) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
