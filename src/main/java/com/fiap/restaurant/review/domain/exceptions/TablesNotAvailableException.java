package com.fiap.restaurant.review.domain.exceptions;

public class TablesNotAvailableException extends RuntimeException {
    public TablesNotAvailableException() {
        super("Not have available tables");
    }
}
