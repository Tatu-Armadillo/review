package com.fiap.restaurant.review.application.records.restaruant;

import java.time.LocalTime;

import com.fiap.restaurant.review.infra.models.RestaurantModel;

public record RestaurantRecord(
        String name,
        String phone,
        String foodType,
        LocalTime openHour,
        LocalTime closeHour,
        Boolean alwaysOpen,
        Integer totalCapacity,
        AddressRecord address) {

    public static RestaurantModel toEntity(final RestaurantRecord record) {
        final var entity = new RestaurantModel();
        entity.setName(record.name);
        entity.setPhone(record.phone);
        entity.setFoodType(record.foodType);
        entity.setOpenHour(record.openHour);
        entity.setCloseHour(record.closeHour);
        entity.setAlwaysOpen(record.alwaysOpen);
        entity.setTotalCapacity(record.totalCapacity);
        entity.setAddress(AddressRecord.toEntity(record.address));
        return entity;
    }

    public static RestaurantRecord toRecord(final RestaurantModel entity) {
        return new RestaurantRecord(
                entity.getName(),
                entity.getPhone(),
                entity.getFoodType(),
                entity.getOpenHour(),
                entity.getCloseHour(),
                entity.getAlwaysOpen(),
                entity.getTotalCapacity(),
                AddressRecord.toRecord(entity.getAddress()));
    }

}
