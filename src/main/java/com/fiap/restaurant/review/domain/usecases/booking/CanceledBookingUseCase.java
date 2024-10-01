package com.fiap.restaurant.review.domain.usecases.booking;

import java.time.LocalDate;

import com.fiap.restaurant.review.domain.gateway.booking.CanceledBookingInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.output.booking.CanceledBookingOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CanceledBookingUseCase {

    private final CanceledBookingInterface canceledBookingRepository;
    private OutputInterface canceldBookingOutput;

    public void execute(final String cpf, LocalDate reservedDate) {

        final var entity = this.canceledBookingRepository.canceledBooking(cpf, reservedDate);

        this.canceldBookingOutput = new CanceledBookingOutput(entity, new OutputStatus(
                200, "Update", "Canceled Booking and Clear Table"));
    }
}
