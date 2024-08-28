package com.fiap.restaurant.review.domain.entities;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.fiap.restaurant.review.domain.exceptions.UserInvalidCpfException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Long id;
    private String cpf;
    private String phone;
    private String username;
    private String fullName;
    private String password;


    public void verifyCpf() throws UserInvalidCpfException {
        if(this.cpf == null || this.cpf.length() != 11){
            throw new UserInvalidCpfException("Invalid CPF: CPF must be 11 characters long.");
        }
    }

}
