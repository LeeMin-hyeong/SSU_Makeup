package com.example.ssu_makeup.custom_class;

public class Review{
    private final float reviewScore;
    private final String reviewer;
    private final String review;

    public Review(float reviewScore, String reviewer, String review){
        this.reviewScore=reviewScore;
        this.reviewer=reviewer;
        this.review=review;
    }

    public float getReviewScore() {
        return reviewScore;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getReview() {
        return review;
    }
}
