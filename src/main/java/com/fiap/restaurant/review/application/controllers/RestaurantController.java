package com.fiap.restaurant.review.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.restaruant.ListRestaurantRecord;
import com.fiap.restaurant.review.application.records.restaruant.RestaurantRecord;
import com.fiap.restaurant.review.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.configuration.web.response.ResponseBasePagination;
import com.fiap.restaurant.review.domain.services.RestaurantService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/restaurant")
@Tag(name = "Restaurant", description = "Endpoints for Managing Restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(final RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    @Operation(summary = "Show Resturants", description = "find all resturant order by agree", tags = {
            "Restaurant" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ListRestaurantRecord.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ListRestaurantRecord.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBasePagination<List<ListRestaurantRecord>>> showResturants(
            @PageableDefault(sort = "totalGrade", direction = Direction.ASC) final Pageable pageable,
            @RequestParam(required = false, defaultValue = "") final String name) {
        final var response = this.restaurantService.showResturants(pageable, name);
        final var base = ResponseBasePagination.of(response.map(ListRestaurantRecord::toRecord));
        return ResponseEntity.ok(base);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create new Restaurant", description = "Save information by Restaurant and address", tags = {
            "Restaurant" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantRecord.class))),
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
