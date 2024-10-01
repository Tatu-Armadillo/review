package com.fiap.restaurant.review.domain.input.booking;

import java.time.LocalDate;

public record CanceledBokingInput(
        String cpf,
        LocalDate reservedDate) {

}
