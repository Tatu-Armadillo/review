package com.fiap.restaurant.review.application.controllers.table;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.booking.SimpleTableRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.table.SaveTablesInput;
import com.fiap.restaurant.review.domain.usecases.table.SaveTableUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.tables.SaveTableRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.TableRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/table/save")
@Tag(name = "Table", description = "Endpoints for Managing Tables")
public class SaveTableController {

        private final TableRepository tableRepository;
        private final RestaurantRepository restaurantRepository;
        

        @PostMapping
        @Transactional
        @Operation(summary = "Create new table", description = "Save information by table", tags = {
                        "Table" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", schema = @Schema(implementation = SimpleTableRecord.class)),
                                                        @Content(mediaType = "application/xml", schema = @Schema(implementation = SimpleTableRecord.class)) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<Object> save(@RequestBody final SimpleTableRecord record) {
                return ResponseEntity.ok(this.getOutputInterface(record));
        }

        private OutputInterface getOutputInterface(final SimpleTableRecord record) {
                final var useCase = new SaveTableUseCase(
                                new SaveTableRepository(tableRepository, restaurantRepository));
                useCase.execute(new SaveTablesInput(record.capacity(), record.resturantCnpj()));
                return useCase.getSaveBookingsOutput();
        }

}
