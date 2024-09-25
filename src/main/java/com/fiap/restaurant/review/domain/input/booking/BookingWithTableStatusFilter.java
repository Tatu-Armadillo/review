package com.fiap.restaurant.review.domain.input.booking;

import java.time.LocalDate;

public record BookingWithTableStatusFilter(
        String cnpj,
        LocalDate filterDay) {

}
