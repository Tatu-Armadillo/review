package com.fiap.restaurant.review.domain.entities;


import com.fiap.restaurant.review.domain.exceptions.UserInvalidCpfException;
import com.fiap.restaurant.review.infra.models.UserModel;

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

    public UserModel toModel() {
        UserModel userModel = new UserModel();
        userModel.setCpf(this.cpf);
        userModel.setFullName(this.fullName);
        userModel.setPassword(this.password);
        userModel.setPhone(this.phone);
        userModel.setUsername(this.username);
        return userModel;
    }


    public void verifyCpf() throws UserInvalidCpfException {
        if(this.cpf == null || this.cpf.length() != 11){
            throw new UserInvalidCpfException("Invalid CPF: CPF must be 11 characters long.");
        }
    }

}
