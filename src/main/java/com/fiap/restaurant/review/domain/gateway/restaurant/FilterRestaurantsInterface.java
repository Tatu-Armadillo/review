package com.fiap.restaurant.review.domain.gateway.restaurant;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.input.restaurant.FilterRestaurantsInput;

public interface FilterRestaurantsInterface {
    List<RestaurantEntity> filterRestaurants(FilterRestaurantsInput filterRestaurantsInput);

}
