package com.fiap.restaurant.review.domain.gateway.review;

import com.fiap.restaurant.review.domain.entities.review.ReviewEntity;

public interface PostReviewInterface {
    void postReview(ReviewEntity reviewEntity);
}
