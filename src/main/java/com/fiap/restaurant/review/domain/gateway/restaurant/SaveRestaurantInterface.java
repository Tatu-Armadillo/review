package com.fiap.restaurant.review.domain.gateway.restaurant;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;

public interface SaveRestaurantInterface {
    void saveRestaurant(RestaurantEntity restaurantEntity);
}
