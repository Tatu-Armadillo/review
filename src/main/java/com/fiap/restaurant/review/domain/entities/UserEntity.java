package com.fiap.restaurant.review.domain.entities;


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



    public UserEntity(String cpf, String phone, String username, String fullName, String password) {
        this.cpf = cpf;
        this.phone = phone;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public UserEntity(Long id, String cpf, String phone, String username, String fullName, String password) {
        this.id = id;
        this.cpf = cpf;
        this.phone = phone;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }


    public void verifyCpf() throws UserInvalidCpfException {
        if(this.cpf == null || this.cpf.length() != 11){
            throw new UserInvalidCpfException("Invalid CPF: CPF must be 11 characters long.");
        }
    }

}
