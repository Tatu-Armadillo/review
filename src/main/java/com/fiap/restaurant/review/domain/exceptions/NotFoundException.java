package com.fiap.restaurant.review.domain.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String mensagem) {
        super(mensagem);
    }
}