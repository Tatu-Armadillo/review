package com.fiap.restaurant.review.infra.adapter.repository.user;

import java.util.Optional;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.domain.gateway.user.GetUserByCpfInterface;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserByCpfRepository implements GetUserByCpfInterface{

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserByCpf(String cpf) throws NotFoundException{
        Optional<UserModel> userModel = this.userRepository.findUserByCpf(cpf);
        if(userModel.get() == null){
            throw new NotFoundException("Produto não encontrado");
        }
        UserEntity userEntity = new UserEntity(
            userModel.get().getId(),
            userModel.get().getCpf(),
            userModel.get().getPhone(),
            userModel.get().getUsername(),
            userModel.get().getFullName(),
            userModel.get().getPassword()
            );

        return userEntity;
    }

}
