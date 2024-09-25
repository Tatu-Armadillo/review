package com.fiap.restaurant.review.domain.gateway.booking;

import java.time.LocalDateTime;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;

public interface CanceledBookingInterface {

    BookingsEntity canceledBooking(String cpf, LocalDateTime reservedDate);
}
