package com.fiap.restaurant.review.infra.adapter.repository.user;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.gateway.user.SaveUserInterface;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveUserRepository implements SaveUserInterface{

    private final UserRepository userRepository;

    @Override 
    public void saveUser(UserEntity userEntity){
        UserModel userModel = new UserModel();
        userModel.setCpf(userEntity.getCpf());
        userModel.setFullName(userEntity.getFullName());
        userModel.setPassword(userEntity.getPassword());
        userModel.setPhone(userEntity.getPhone());
        userModel.setUsername(userEntity.getUsername());
        this.userRepository.save(userModel);
    }

}
