package com.fiap.restaurant.review.application.controllers.mock;

import com.fiap.restaurant.review.infra.models.BookingModel;

import java.time.LocalDateTime;

public class BookingModelTestData {

    public static BookingModel createBooking() {
        return new BookingModel(1L, 4, LocalDateTime.now(), false,
                UserModelTestData.createUser(), TableModelTestData.createTable());
    }
}
