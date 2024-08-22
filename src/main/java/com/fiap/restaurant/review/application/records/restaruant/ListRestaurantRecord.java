package com.fiap.restaurant.review.application.records.restaruant;

import com.fiap.restaurant.review.infra.models.Restaurant;

public record ListRestaurantRecord(
        String name,
        String foodType,
        Integer grade,
        String phone) {

    public static ListRestaurantRecord toRecord(final Restaurant entity) {
        return new ListRestaurantRecord(
                entity.getName(),
                entity.getFoodType(),
                entity.getTotalGrade(),
                entity.getPhone());

    }

}
