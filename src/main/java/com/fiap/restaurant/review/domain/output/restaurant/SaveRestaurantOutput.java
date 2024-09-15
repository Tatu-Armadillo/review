package com.fiap.restaurant.review.domain.output.restaurant;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@RequiredArgsConstructor
public class SaveRestaurantOutput implements OutputInterface{

    private RestaurantEntity restaurantEntity;
    private OutputStatus outputStatus;

    public SaveRestaurantOutput(RestaurantEntity restaurantEntity, OutputStatus outputStatus) {
        this.restaurantEntity = restaurantEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.restaurantEntity;
    }

}
