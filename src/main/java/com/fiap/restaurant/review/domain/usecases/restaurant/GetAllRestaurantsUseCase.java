package com.fiap.restaurant.review.domain.usecases.restaurant;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.GetAllRestaurantsInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.output.restaurant.GetAllRestaurantsOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetAllRestaurantsUseCase {
    private final GetAllRestaurantsInterface getAllRestaurantsRepository;
    private OutputInterface getAllRestaurantOutput;

    public void execute(){
        List<RestaurantEntity> listRestaurantsEntities = this.getAllRestaurantsRepository.getAllRestaurants();
        this.getAllRestaurantOutput = new GetAllRestaurantsOutput(listRestaurantsEntities,
        new OutputStatus(200,
         "OK",
         "List of restaurants found!"));
    }




}
