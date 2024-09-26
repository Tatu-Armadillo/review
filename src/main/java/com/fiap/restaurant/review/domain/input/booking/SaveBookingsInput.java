package com.fiap.restaurant.review.domain.input.booking;

import java.time.LocalDateTime;

public record SaveBookingsInput(
        String cnpjRestaurant,
        String cpfBook,
        Integer quantityPeople,
        LocalDateTime dateBook) {

}
