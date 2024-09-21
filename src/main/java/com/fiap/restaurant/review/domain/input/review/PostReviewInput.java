package com.fiap.restaurant.review.domain.input.review;

public record PostReviewInput(
    Integer grade,
    String comment,
    Long userId,
    Long restaurantId
) {

}
