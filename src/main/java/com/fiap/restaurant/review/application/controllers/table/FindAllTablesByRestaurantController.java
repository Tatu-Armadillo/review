package com.fiap.restaurant.review.application.controllers.table;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.booking.SimpleTableRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.usecases.table.FindAllTablesByRestaurantUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.tables.FindAllTablesByRestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.TableRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/table/all")
@Tag(name = "Table", description = "Endpoints for Managing Restaurant")
public class FindAllTablesByRestaurantController {

        private final TableRepository tableRepository;

        @GetMapping
        @Operation(summary = "Show Resturants", description = "find all resturant order by agree", tags = {
                        "Table" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", schema = @Schema(implementation = SimpleTableRecord.class)),
                                                        @Content(mediaType = "application/xml", schema = @Schema(implementation = SimpleTableRecord.class)) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<Object> findAllTablesByRestaurant(
                        @RequestParam(required = false, defaultValue = "") final String cnpj) {
                OutputInterface output = this.getOutputInterface(cnpj);
                return ResponseEntity.ok(output);
        }

        private OutputInterface getOutputInterface(final String cnpj) {
                final var useCase = new FindAllTablesByRestaurantUseCase(
                                new FindAllTablesByRestaurantRepository(tableRepository));
                useCase.execute(cnpj);
                return useCase.getFindAllTablesOutput();
        }

}
