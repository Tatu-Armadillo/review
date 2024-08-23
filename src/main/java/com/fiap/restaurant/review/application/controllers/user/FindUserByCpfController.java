package com.fiap.restaurant.review.application.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.user.UserRecord;
import com.fiap.restaurant.review.domain.services.UserService;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user/find")
@Tag(name = "Users", description = "Endpoints for Managing users")
public class FindUserByCpfController {

    private final UserService userService;

    @Autowired
    public FindUserByCpfController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Find User", description = "Find user by CPF", tags = {
            "Users" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserRecord.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = UserRecord.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<UserRecord>> findUserByCpf(
            @RequestParam(required = true, defaultValue = "") final String name) {
        final var response = this.userService.findUserByCpf(name);
        final var base = ResponseBase.of(UserRecord.toRecord(response));
        return ResponseEntity.ok(base);
    }

}
