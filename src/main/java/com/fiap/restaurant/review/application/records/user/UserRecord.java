package com.fiap.restaurant.review.application.records.user;


public record UserRecord(
        String cpf,
        String phone,
        String fullName,
        String username,
        String password) {


}
