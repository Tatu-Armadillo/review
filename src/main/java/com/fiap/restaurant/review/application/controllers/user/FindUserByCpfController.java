package com.fiap.restaurant.review.application.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.response.GenericResponse;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.usecases.user.GetUserByCpfUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.user.GetUserByCpfRepository;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/find")
@Tag(name = "Users", description = "Endpoints for Managing users")
public class FindUserByCpfController {


    private final UserRepository userRepository;


    @GetMapping
    @Operation(summary = "Find User", description = "Find user by CPF", tags = {
            "Users" })
    public ResponseEntity<Object> findUserByCpf(
            @RequestParam(required = true, defaultValue = "") final String cpf) {

                
                OutputInterface outputInterface = this.getOutputInterface(cpf);
                return new GenericResponse().response(outputInterface);
    }

    private OutputInterface getOutputInterface(String cpf){
                GetUserByCpfUseCase useCase = new GetUserByCpfUseCase(new GetUserByCpfRepository(userRepository));
                useCase.execute(cpf);
                return useCase.getGetUserByCpfOutput();
                                
        }

}
