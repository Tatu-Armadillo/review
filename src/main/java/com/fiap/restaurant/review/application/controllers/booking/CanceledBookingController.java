package com.fiap.restaurant.review.application.controllers.booking;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.booking.CanceledBokingInput;
import com.fiap.restaurant.review.domain.usecases.booking.CanceledBookingUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.booking.CanceledBookingRepository;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking/canceled")
@Tag(name = "Bookings", description = "Endpoints for canceled and clear the table")
public class CanceledBookingController {

    private final BookingRepositoy bookingRepositoy;

    @PatchMapping
    @Transactional
    @Operation(summary = "Canceled Bookings", description = "Clear table and cacneled booking", tags = {
            "Bookings" })
    public ResponseEntity<Object> canceledBookings(@RequestBody final CanceledBokingInput input) {
        OutputInterface outputInterface = this.getOutputInterface(input.cpf(), input.reservedDate());
        return ResponseEntity.ok(outputInterface.getBody());
    }

    private OutputInterface getOutputInterface(String cpf, LocalDate reservedDate) {
        final var useCase = new CanceledBookingUseCase(
                new CanceledBookingRepository(bookingRepositoy));

        useCase.execute(cpf, reservedDate);
        return useCase.getCanceldBookingOutput();
    }

}
