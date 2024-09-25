package com.fiap.restaurant.review.application.controllers.mock;

import com.fiap.restaurant.review.infra.models.RestaurantModel;

import java.time.LocalTime;

public class RestaurantModelTestData {
    public static RestaurantModel createRestaurant() {
        return new RestaurantModel(1L, "restaurant", "92010209000154", "35991697607", "comida",
                LocalTime.now(), LocalTime.now(), true, 20, 30, AddressModelTestData.createAddress());
    }
}
