package com.fiap.restaurant.review.domain.input.restaurant;

public record FilterRestaurantsInput(
    String name,
    String city,
    String foodType
) {

}
