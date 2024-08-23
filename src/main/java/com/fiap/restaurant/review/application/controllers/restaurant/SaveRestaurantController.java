package com.fiap.restaurant.review.application.controllers.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.restaruant.RestaurantRecord;
import com.fiap.restaurant.review.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.domain.services.resturant.RestaurantService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/restaurant/save")
@Tag(name = "Restaurant", description = "Endpoints for Managing Restaurant")
public class SaveRestaurantController {
        
        private final RestaurantService restaurantService;

        @Autowired
        public SaveRestaurantController(final RestaurantService restaurantService) {
                this.restaurantService = restaurantService;
        }

        @PostMapping
        @Transactional
        @Operation(summary = "Create new Restaurant", description = "Save information by Restaurant and address", tags = {
                        "Restaurant" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantRecord.class)),
                                                        @Content(mediaType = "application/xml", schema = @Schema(implementation = RestaurantRecord.class)) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<RestaurantRecord>> save(
                        @RequestBody final RestaurantRecord record) {
                final var response = this.restaurantService.save(RestaurantRecord.toEntity(record));
                final var base = ResponseBase.of(RestaurantRecord.toRecord(response));
                return ResponseEntity.ok(base);
        }

}
