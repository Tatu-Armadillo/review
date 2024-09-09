package com.fiap.restaurant.review.domain.usecases.user;

import com.fiap.restaurant.review.domain.entities.UserEntity;
import com.fiap.restaurant.review.domain.gateway.user.SaveUserInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.input.user.SaveUserInput;
import com.fiap.restaurant.review.domain.output.user.SaveUserOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SaveUserUseCase {

    private final SaveUserInterface saveUserRepository;
    private OutputInterface saveUserOutput;

    public void execute(SaveUserInput saveUserInput){
        UserEntity userEntity = new UserEntity(
            saveUserInput.cpf(),
            saveUserInput.phone(),
            saveUserInput.username(),
            saveUserInput.fullName(),
            saveUserInput.password()
        );
        this.saveUserRepository.saveUser(userEntity);
        this.saveUserOutput = new SaveUserOutput(userEntity, new OutputStatus(201, "Created", "User created"));
    }

}
