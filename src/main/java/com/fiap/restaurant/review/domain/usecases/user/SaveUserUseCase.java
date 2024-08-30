package com.fiap.restaurant.review.domain.usecases.user;
import com.fiap.restaurant.review.infra.models.UserModel;

import com.fiap.restaurant.review.domain.entities.UserEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.generic.output.UserOutput;
import com.fiap.restaurant.review.domain.input.user.SaveUserInput;
import com.fiap.restaurant.review.domain.output.user.SaveUserOutput;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SaveUserUseCase {

    private UserRepository userRepository;
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
        UserModel userModel = this.userRepository.findUserByCpf(userEntity.getCpf())
                .orElseGet(() -> this.userRepository.save(userEntity.toModel()));
        this.saveUserOutput = new saveUserOutput(userModel, new OutputStatus(201, "Created", "User created"));
        } catch (Exception exc){

        }
        finally{
            if (this.saveUserOutput instanceof SaveUserOutput saveUserOutput){
                UserOutput userOutput = new UserOutput();

            }
        }

    }

}
