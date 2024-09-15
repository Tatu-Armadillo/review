package com.fiap.restaurant.review.application.controllers.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.restaurant.ListRestaurantRecord;
import com.fiap.restaurant.review.domain.services.resturant.RestaurantService;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBasePagination;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/restaurant/all")
@Tag(name = "Restaurant", description = "Endpoints for Managing Restaurant")
public class FindAllResturantController {

    private final RestaurantService restaurantService;

    @Autowired
    public FindAllResturantController(final RestaurantService restaurantService) {
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
        final var response = this.restaurantService.findAllAndPageableByFilter(pageable, name);
        final var base = ResponseBasePagination.of(response.map(ListRestaurantRecord::toRecord));
        return ResponseEntity.ok(base);
    }

}
