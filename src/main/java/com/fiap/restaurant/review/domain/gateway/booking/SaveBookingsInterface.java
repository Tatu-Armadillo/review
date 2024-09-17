package com.fiap.restaurant.review.domain.gateway.booking;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;

public interface SaveBookingsInterface {
    void saveBookings(final BookingsEntity bookingsEntity);
}
