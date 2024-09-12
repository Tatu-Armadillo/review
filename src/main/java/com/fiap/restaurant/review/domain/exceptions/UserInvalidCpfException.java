package com.fiap.restaurant.review.domain.exceptions;

public class UserInvalidCpfException extends Exception {
    public UserInvalidCpfException (final String message){
        super(message);
    }
}