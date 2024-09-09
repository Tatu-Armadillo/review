package com.fiap.restaurant.review.domain.input.user;


public record SaveUserInput( 
    String cpf,
    String phone,
    String username,
    String fullName,
    String password
){

}
