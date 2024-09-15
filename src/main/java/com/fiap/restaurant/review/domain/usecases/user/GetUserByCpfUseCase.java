package com.fiap.restaurant.review.domain.usecases.user;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.domain.gateway.user.GetUserByCpfInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputError;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetUserByCpfUseCase {

    private final GetUserByCpfInterface getUserByCpfRepository;
    private OutputInterface getUserByCpfOutput;

    public void execute(String cpf){
        // UserEntity userEntity = this.GetUserByCpfRepository.getUserByCpf(cpf);
        // if(userEntity != null){
        // this.getUserByCpfOutput = new com.fiap.restaurant.review.domain.output.user.GetUserByCpfOutput(userEntity,
        // new OutputStatus(200,
        //  "OK",
        //  "User found!"));
        // } else{
        // this.getUserByCpfOutput = new OutputError(
        //         "User Not Found",
        //         new OutputStatus(404, "Not Found", "User Not Found")
        //     );    
        // }

        //NoSuchElementException
        try{
        UserEntity userEntity = this.getUserByCpfRepository.getUserByCpf(cpf);
        this.getUserByCpfOutput = new com.fiap.restaurant.review.domain.output.user.GetUserByCpfOutput(userEntity,
        new OutputStatus(200,
         "OK",
         "User found!"));
        } catch (NotFoundException exceptions){
        this.getUserByCpfOutput = new OutputError(
                "User Not Found",
                new OutputStatus(404, "Not Found", "User Not Found")
            );
        }
        


}
}
