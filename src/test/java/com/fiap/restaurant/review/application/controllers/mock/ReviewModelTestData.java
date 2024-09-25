package com.fiap.restaurant.review.application.controllers.mock;

import com.fiap.restaurant.review.infra.models.ReviewModel;

import java.time.LocalDateTime;

public class ReviewModelTestData {

    public static ReviewModel createReview() {
        return new ReviewModel(null, LocalDateTime.now(), 5, "comment", 1L, 1L);
    }
}
