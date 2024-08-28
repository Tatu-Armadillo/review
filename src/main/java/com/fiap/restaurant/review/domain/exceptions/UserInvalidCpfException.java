package com.fiap.restaurant.review.domain.exceptions;

public class UserInvalidCpfException extends RuntimeException {
    public UserInvalidCpfException (final String message){
        super(message);
    }
}