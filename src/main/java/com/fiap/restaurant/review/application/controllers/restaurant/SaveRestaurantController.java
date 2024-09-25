package com.fiap.restaurant.review.application.controllers.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.restaurant.RestaurantRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.restaurant.AddressRestaurantInput;
import com.fiap.restaurant.review.domain.input.restaurant.SaveRestaurantInput;
import com.fiap.restaurant.review.domain.usecases.restaurant.SaveRestaurantUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.restaurant.SaveRestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
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
        private final AddressRepository addressRepository;

        @PostMapping
        @Transactional
        @Operation(summary = "Create new Restaurant", description = "Save information by Restaurant and address", tags = {
                        "Restaurant" })
        public ResponseEntity<Object> save(
                        @RequestBody final RestaurantRecord restaurantRecord) {
                OutputInterface outputInterface = this.getOutputInterface(restaurantRecord);
                return ResponseEntity.ok(outputInterface.getBody());
        }

        private OutputInterface getOutputInterface(RestaurantRecord restaurantRecord) {
                SaveRestaurantInput saveRestaurantInput = new SaveRestaurantInput(
                                restaurantRecord.name(),
                                restaurantRecord.cnpj(),
                                restaurantRecord.phone(),
                                restaurantRecord.foodType(),
                                restaurantRecord.openHour(),
                                restaurantRecord.closeHour(),
                                restaurantRecord.alwaysOpen(),
                                restaurantRecord.totalCapacity(),
                                new AddressRestaurantInput(
                                                restaurantRecord.address().cep(),
                                                restaurantRecord.address().publicPlace(),
                                                restaurantRecord.address().complement(),
                                                restaurantRecord.address().neighborhood(),
                                                restaurantRecord.address().city(),
                                                restaurantRecord.address().ufState(),
                                                restaurantRecord.address().latitude(),
                                                restaurantRecord.address().longitude()));
                SaveRestaurantUseCase useCase = new SaveRestaurantUseCase(
                                new SaveRestaurantRepository(restaurantRepository, addressRepository));
                useCase.execute(saveRestaurantInput);
                return useCase.getSaveRestaurantOutput();

        }

}
