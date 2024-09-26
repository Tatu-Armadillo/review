package com.fiap.restaurant.review.domain.gateway.tables;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;

public interface FindAllTablesByRestaurantInterface {
    List<TableEntity> findAllTablesByRestaurant(String cpnj);
}
