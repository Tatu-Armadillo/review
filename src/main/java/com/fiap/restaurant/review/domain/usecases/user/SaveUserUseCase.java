package com.fiap.restaurant.review.domain.usecases.user;

import com.fiap.restaurant.review.domain.entities.UserEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.user.SaveUserInput;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SaveUserUseCase {

    private UserRepository userRepository;
    private OutputInterface buscaPedidoOutput;

    public void execute(SaveUserInput saveUserInput){
        try{
        UserEntity userEntity = new UserEntity(
            
        );
        } catch (Exception exc){

        }

    }

}
