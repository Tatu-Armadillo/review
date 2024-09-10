package com.fiap.restaurant.review.domain.usecases.user;

import com.fiap.restaurant.review.domain.entities.UserEntity;
import com.fiap.restaurant.review.domain.gateway.user.GetUserByCpfInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetUserByCpfUseCase {

    private final GetUserByCpfInterface GetUserByCpfRepository;
    private OutputInterface GetUserByCpfOutput;

    public void execute(String cpf){
        UserEntity userEntity = this.GetUserByCpfRepository.getUserByCpf(cpf);
        this.GetUserByCpfOutput = new com.fiap.restaurant.review.domain.output.user.GetUserByCpfOutput(userEntity,
        new OutputStatus(200,
         "OK",
         "User found!"));
    }

}
