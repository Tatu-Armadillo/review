package com.fiap.restaurant.review.application.controllers.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.restaurant.RestaurantRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.restaurant.AddressRestaurantInput;
import com.fiap.restaurant.review.domain.input.restaurant.SaveRestaurantInput;
import com.fiap.restaurant.review.domain.usecases.restaurant.SaveRestaurantUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.restaurant.SaveRestaurantRepository;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant/save")
@Tag(name = "Restaurant", description = "Endpoints for Managing Restaurant")
public class SaveRestaurantController {
        
        private final RestaurantRepository restaurantRepository;



        @PostMapping
        @Transactional
        @Operation(summary = "Create new Restaurant", description = "Save information by Restaurant and address", tags = {
                        "Restaurant" })
        public ResponseEntity<ResponseBase<Object>> save(
                        @RequestBody final RestaurantRecord restaurantRecord) {
                OutputInterface outputInterface = this.getOutputInterface(restaurantRecord);
                return ResponseEntity.ok(ResponseBase.of(outputInterface.getBody()));
        }

        private OutputInterface getOutputInterface(RestaurantRecord restaurantRecord){
                SaveRestaurantInput saveRestaurantInput = new SaveRestaurantInput(
                        restaurantRecord.name(),
                        restaurantRecord.phone(),
                        restaurantRecord.foodType(),
                        restaurantRecord.cnpj(),
                        restaurantRecord.openHour(),
                        restaurantRecord.closeHour(),
                        restaurantRecord.alwaysOpen(),
                        restaurantRecord.totalCapacity(),
                        new AddressRestaurantInput(null, null, null, null, null, null, null, null)
                        );
                SaveRestaurantUseCase useCase = new SaveRestaurantUseCase(new SaveRestaurantRepository(restaurantRepository));
                useCase.execute(saveRestaurantInput);
                return useCase.getSaveRestaurantOutput();
                                
        }

}
