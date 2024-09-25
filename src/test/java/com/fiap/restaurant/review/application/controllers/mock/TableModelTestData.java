package com.fiap.restaurant.review.application.controllers.mock;

import com.fiap.restaurant.review.infra.models.TableModel;

import java.util.HashSet;

public class TableModelTestData {

    public static TableModel createTable() {
        return new TableModel(1L, 10, true, RestaurantModelTestData.createRestaurant(), new HashSet<>());
    }
}
