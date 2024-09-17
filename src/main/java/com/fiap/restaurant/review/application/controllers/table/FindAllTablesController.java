package com.fiap.restaurant.review.application.controllers.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.booking.SimpleTableRecord;
import com.fiap.restaurant.review.domain.services.table.TableService;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBasePagination;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/table/all")
@Tag(name = "Table", description = "Endpoints for Managing Restaurant")
public class FindAllTablesController {

    private final TableService tableService;

    @Autowired
    public FindAllTablesController(final TableService tableService) {
        this.tableService = tableService;
    }

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
    public ResponseEntity<ResponseBasePagination<List<SimpleTableRecord>>> showResturants(
            @PageableDefault(sort = "id", direction = Direction.ASC) final Pageable pageable,
            @RequestParam(required = false, defaultValue = "") final String cnpj) {
        final var response = this.tableService.findAllTablesByResturant(pageable, cnpj);
        final var base = ResponseBasePagination.of(response.map(SimpleTableRecord::toRecord));
        return ResponseEntity.ok(base);
    }

}
