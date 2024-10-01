package com.fiap.restaurant.review.domain.gateway.booking;

import java.time.LocalDate;
import java.util.List;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;

public interface GetAllTablesWithBookingsInterface {
    List<BookingsEntity> getAllTablesWithBookingsAndResturant(String cnpjResturant, LocalDate reservedDate);
}
