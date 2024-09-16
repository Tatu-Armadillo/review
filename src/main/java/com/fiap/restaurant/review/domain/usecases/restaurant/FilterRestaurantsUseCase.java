package com.fiap.restaurant.review.domain.usecases.restaurant;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.FilterRestaurantsInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.input.restaurant.FilterRestaurantsInput;
import com.fiap.restaurant.review.domain.output.restaurant.FilterRestaurantsOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FilterRestaurantsUseCase {
    private final FilterRestaurantsInterface filterRestaurantsRepository;
    private OutputInterface filterRestaurantOutput;

    public void execute(FilterRestaurantsInput filterRestaurantsInput){
        List<RestaurantEntity> listRestaurantsEntities = this.filterRestaurantsRepository.filterRestaurants(
            filterRestaurantsInput);
        this.filterRestaurantOutput = new FilterRestaurantsOutput(listRestaurantsEntities,
        new OutputStatus(200,
         "OK",
         "List of restaurants found!"));


    }

}
