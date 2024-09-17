package com.fiap.restaurant.review.application.records.review;


public record PostReviewRecord(
    Integer grade,
    String comment,
    Long userId,
    Long restaurantId
) {

}
