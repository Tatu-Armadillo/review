package com.fiap.restaurant.review.application.controllers.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.usecases.restaurant.GetAllRestaurantsUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.restaurant.GetAllRestaurantsRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant/all")
@Tag(name = "Restaurant", description = "Endpoints for Managing Restaurant")
public class FindAllResturantController {

        private final RestaurantRepository restaurantRepository;

    @GetMapping
    @Operation(summary = "Show Resturants", description = "find all resturant order by agree", tags = {
            "Restaurant" })
    public ResponseEntity<Object> showAllResturants() {
        OutputInterface outputInterface = this.getOutputInterface();
        return ResponseEntity.ok(outputInterface.getBody());
    }

    private OutputInterface getOutputInterface(){
        GetAllRestaurantsUseCase useCase = new GetAllRestaurantsUseCase(new GetAllRestaurantsRepository(restaurantRepository));
        useCase.execute();
        return useCase.getGetAllRestaurantOutput();
    }

}
