package com.fiap.restaurant.review.domain.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(final String mensagem) {
        super(mensagem);
    }
}