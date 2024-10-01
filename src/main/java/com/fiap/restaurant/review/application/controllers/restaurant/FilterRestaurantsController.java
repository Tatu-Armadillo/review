package com.fiap.restaurant.review.application.controllers.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.restaurant.review.application.records.restaurant.FilterRestaurantRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.restaurant.FilterRestaurantsInput;
import com.fiap.restaurant.review.domain.usecases.restaurant.FilterRestaurantsUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.restaurant.FilterRestaurantsRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant/filter")
@Tag(name = "Restaurant", description = "Endpoints for Managing Restaurant")
public class FilterRestaurantsController {

    private final RestaurantRepository restaurantRepository;

    @GetMapping
    @Operation(
    summary = "Show Resturants",
    description = "Filter restaurants by name, location or food type",
    tags = {"Restaurant"})
    public ResponseEntity<Object> FilterResturants(@RequestBody FilterRestaurantRecord filterRestaurantRecord) {
        OutputInterface outputInterface = this.getOutputInterface(filterRestaurantRecord);
        return ResponseEntity.ok(outputInterface.getBody());
    }

    private OutputInterface getOutputInterface(FilterRestaurantRecord filterRestaurantRecord){
        FilterRestaurantsUseCase useCase = new FilterRestaurantsUseCase(
        new FilterRestaurantsRepository(restaurantRepository));
        useCase.execute(new FilterRestaurantsInput(
            filterRestaurantRecord.name(),
            filterRestaurantRecord.city(),
            filterRestaurantRecord.foodType()));
        return useCase.getFilterRestaurantOutput();
    }


}
