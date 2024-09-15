package com.fiap.restaurant.review.domain.entities.user;


import com.fiap.restaurant.review.domain.entities.user.validations.SaveUserValidation;
import com.fiap.restaurant.review.domain.exceptions.UserInvalidCpfException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {

    private Long id;
    private String cpf;
    private String phone;
    private String username;
    private String fullName;
    private String password;



    public UserEntity(String cpf, String phone, String username, String fullName, String password) throws UserInvalidCpfException {
        this.cpf = cpf;
        this.phone = phone;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        validateUser();
    }

    public UserEntity(Long id, String cpf, String phone, String username, String fullName, String password) {
        this.id = id;
        this.cpf = cpf;
        this.phone = phone;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public UserEntity validateUser() throws UserInvalidCpfException {
        return new SaveUserValidation().validateEntity(this);
    }

}
