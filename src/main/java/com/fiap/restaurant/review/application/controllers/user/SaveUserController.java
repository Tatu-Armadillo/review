package com.fiap.restaurant.review.application.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.user.UserRecord;
import com.fiap.restaurant.review.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.domain.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user/save")
@Tag(name = "Users", description = "Endpoints for Managing users")
public class SaveUserController {

        private final UserService userService;

        @Autowired
        public SaveUserController(final UserService userService) {
                this.userService = userService;
        }

        @PostMapping
        @Transactional
        @Operation(summary = "Create new user", description = "Save information by user", tags = {
                        "Users" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserRecord.class)),
                                                        @Content(mediaType = "application/xml", schema = @Schema(implementation = UserRecord.class)) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<UserRecord>> save(@RequestBody final UserRecord record) {
                final var response = this.userService.save(UserRecord.toEntity(record));
                final var base = ResponseBase.of(UserRecord.toRecord(response));
                return ResponseEntity.ok(base);
        }

}
