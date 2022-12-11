package com.example.ssu_makeup;

import java.io.Serializable;

public class Review implements Serializable {
    private final int reviewScore;
    private final String reviewer;
    private final String review;

    public Review(int reviewScore, String reviewer, String review){
        this.reviewScore=reviewScore;
        this.reviewer=reviewer;
        this.review=review;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getReview() {
        return review;
    }
}
