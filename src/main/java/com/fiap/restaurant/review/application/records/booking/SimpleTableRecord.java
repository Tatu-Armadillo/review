package com.fiap.restaurant.review.application.records.booking;

import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.models.TableModel;

public record SimpleTableRecord(
        Integer capacity,
        String resturantCnpj) {

    public static TableModel toEntity(final SimpleTableRecord record) {
        final var entity = new TableModel();
        entity.setCapacity(record.capacity);
        final var restaurant = new RestaurantModel();
        restaurant.setCnpj(record.resturantCnpj);
        entity.setRestaurant(restaurant);
        return entity;
    }

    public static SimpleTableRecord toRecord(final TableModel entity) {
        return new SimpleTableRecord(entity.getCapacity(), entity.getRestaurant().getName());
    }

}
