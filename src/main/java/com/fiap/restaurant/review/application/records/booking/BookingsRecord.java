package com.fiap.restaurant.review.application.records.booking;

import java.time.LocalDateTime;


public record BookingsRecord(
        String cnpjRestaurant,
        String cpfBook,
        Integer quantityPeople,
        LocalDateTime dateBook) {

}
