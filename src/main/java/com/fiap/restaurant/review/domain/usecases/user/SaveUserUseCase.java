package com.fiap.restaurant.review.domain.usecases.user;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.exceptions.UserInvalidCpfException;
import com.fiap.restaurant.review.domain.gateway.user.SaveUserInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputError;
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
        try{
        UserEntity userEntity = new UserEntity(
            saveUserInput.cpf(),
            saveUserInput.phone(),
            saveUserInput.username(),
            saveUserInput.fullName(),
            saveUserInput.password()
        );
        this.saveUserRepository.saveUser(userEntity);
        this.saveUserOutput = new SaveUserOutput(userEntity, new OutputStatus(
            201,
        "Created",
        "User created"
        ));
        }
        catch(UserInvalidCpfException exceptions){
            this.saveUserOutput = new OutputError(
                exceptions.getMessage(),
                new OutputStatus(422, "Unprocessable Entity", exceptions.getMessage())
            );

        }
    }

}
