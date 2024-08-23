package com.fiap.restaurant.review.application.records.restaruant;

import com.fiap.restaurant.review.infra.models.RestaurantModel;

public record ListRestaurantRecord(
        String name,
        String foodType,
        Integer grade,
        String phone) {

    public static ListRestaurantRecord toRecord(final RestaurantModel entity) {
        return new ListRestaurantRecord(
                entity.getName(),
                entity.getFoodType(),
                entity.getTotalGrade(),
                entity.getPhone());

    }

}
