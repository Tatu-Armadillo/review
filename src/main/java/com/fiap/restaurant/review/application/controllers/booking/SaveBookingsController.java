package com.fiap.restaurant.review.application.controllers.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.booking.BookingsRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.booking.SaveBookingsInput;
import com.fiap.restaurant.review.domain.usecases.booking.SaveBookingsUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.booking.SaveBookingRepository;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking/save")
@Tag(name = "Bookings", description = "Endpoints for Managing Bookings")
public class SaveBookingsController {

        private final BookingRepositoy bookingRepositoy;
        private final TableRepository tableRepository;
        private final UserRepository userRepository;

        @PostMapping
        @Transactional
        @Operation(summary = "Bookings", description = "Book table by user", tags = {
                        "Bookings" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", schema = @Schema(implementation = BookingsRecord.class)),
                                                        @Content(mediaType = "application/xml", schema = @Schema(implementation = BookingsRecord.class)) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<Object> save(@RequestBody final BookingsRecord record) {
                OutputInterface outputInterface = this.getOutputInterface(record);
                return ResponseEntity.ok(outputInterface.getBody());
        }

        private OutputInterface getOutputInterface(final BookingsRecord record) {
                final var useCase = new SaveBookingsUseCase(
                                new SaveBookingRepository(bookingRepositoy, tableRepository, userRepository));
                useCase.execute(new SaveBookingsInput(
                                record.cnpjRestaurant(),
                                record.cpfBook(),
                                record.quantityPeople(),
                                record.dateBook()));
                return useCase.getSaveBookingsOutput();
        }

}
