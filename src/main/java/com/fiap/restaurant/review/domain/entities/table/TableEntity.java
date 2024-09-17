package com.fiap.restaurant.review.domain.entities.table;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TableEntity {
    private Long id;
    private Integer capacity;
    private Boolean available;
    private RestaurantEntity restaurant;

    public TableEntity(Integer capacity, Boolean available, RestaurantEntity restaurant) {
        this.capacity = capacity;
        this.available = available;
        this.restaurant = restaurant;
    }

    
}
