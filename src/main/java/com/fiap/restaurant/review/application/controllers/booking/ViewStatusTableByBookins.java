package com.fiap.restaurant.review.application.controllers.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.booking.BookingWithTableStatusFilter;
import com.fiap.restaurant.review.domain.usecases.booking.GetAllTablesWithBookings;
import com.fiap.restaurant.review.infra.adapter.repository.booking.GetAllTablesWithBookingsRepository;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking/status")
@Tag(name = "Bookings", description = "Endpoints for views tables availabes by bookings")
public class ViewStatusTableByBookins {

    private final BookingRepositoy bookingRepositoy;

    @GetMapping
    @Operation(summary = "Show Bookings", description = "find all tables odered by availiable", tags = {
            "Bookings" })
    public ResponseEntity<Object> showAllReviews(@RequestBody BookingWithTableStatusFilter record) {
        OutputInterface outputInterface = this.getOutputInterface(record.cnpj(), record.filterDay());
        return ResponseEntity.ok(outputInterface.getBody());
    }

    private OutputInterface getOutputInterface(String cnpj, LocalDate filterDay) {
        GetAllTablesWithBookings useCase = new GetAllTablesWithBookings(
                new GetAllTablesWithBookingsRepository(bookingRepositoy));

        useCase.execute(cnpj, LocalDateTime.of(filterDay, LocalTime.now()));
        return useCase.getGetAllTablesWithBookingsOutput();
    }

}
