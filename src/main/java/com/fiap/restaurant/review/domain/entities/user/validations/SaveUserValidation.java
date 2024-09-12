package com.fiap.restaurant.review.domain.entities.user.validations;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.exceptions.UserInvalidCpfException;

public class SaveUserValidation {
    public UserEntity validateEntity(UserEntity userEntity) throws UserInvalidCpfException {
        
        if(userEntity.getCpf() == null || userEntity.getCpf().length() != 11){
            throw new UserInvalidCpfException("Invalid CPF: CPF must be 11 characters long.");
        }
        return userEntity;
    }

}
