package com.fiap.restaurant.review.application.controllers.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.restaurant.review.application.records.booking.SimpleTableRecord;
import com.fiap.restaurant.review.domain.services.table.TableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/table/save")
@Tag(name = "Table", description = "Endpoints for Managing Tables")
public class SaveTableWithRestuarantController {

    private final TableService tableService;

    @Autowired
    public SaveTableWithRestuarantController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create new user", description = "Save information by user", tags = {
            "Table" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SimpleTableRecord.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = SimpleTableRecord.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Object> save(@RequestBody final SimpleTableRecord record) {
        final var response = SimpleTableRecord.toRecord(this.tableService.save(SimpleTableRecord.toEntity(record)));
        return ResponseEntity.ok(response);
    }

}
