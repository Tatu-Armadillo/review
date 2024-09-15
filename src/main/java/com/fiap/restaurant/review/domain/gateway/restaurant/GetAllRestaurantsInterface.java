package com.fiap.restaurant.review.domain.gateway.restaurant;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;

public interface GetAllRestaurantsInterface {
    List<RestaurantEntity> getAllRestaurants();

}
