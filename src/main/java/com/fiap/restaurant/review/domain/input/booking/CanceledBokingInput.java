package com.fiap.restaurant.review.domain.input.booking;

import java.time.LocalDateTime;

public record CanceledBokingInput(
        String cpf,
        LocalDateTime reservedDate) {

}
