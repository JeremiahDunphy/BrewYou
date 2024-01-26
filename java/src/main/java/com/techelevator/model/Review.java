package com.techelevator.model;

import java.util.Date;

public class Review {
    private int review_id;

    private int beer_id;

    private int user_id;

    private String review_text;

    private Date date_of_review;

    private double rating;

    private String title;



    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getBeer_id() {
        return beer_id;
    }

    public void setBeer_id(int beer_id) {
        this.beer_id = beer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public Date getDate_of_review() {
        return date_of_review;
    }

    public void setDate_of_review(Date date_of_review) {
        this.date_of_review = date_of_review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




}
