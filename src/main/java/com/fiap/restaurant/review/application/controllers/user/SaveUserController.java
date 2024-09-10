package com.fiap.restaurant.review.application.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.user.UserRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.user.SaveUserInput;
import com.fiap.restaurant.review.domain.usecases.user.SaveUserUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.user.SaveUserRepository;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/save")
@Tag(name = "Users", description = "Endpoints for Managing users")
public class SaveUserController {

        private final UserRepository userRepository;


        @PostMapping
        @Operation(tags = {"Users"})
        public ResponseEntity<ResponseBase<Object>> save(@RequestBody final UserRecord userRecord) {

                OutputInterface outputInterface = this.getOutputInterface(userRecord);
                return ResponseEntity.ok(ResponseBase.of(outputInterface.getBody()));
        }

        private OutputInterface getOutputInterface(UserRecord userRecord){
                SaveUserInput saveUserInput = new SaveUserInput(
                        userRecord.cpf(),
                        userRecord.phone(), 
                        userRecord.username(),
                        userRecord.fullname(),
                        userRecord.password()
                        );
                SaveUserUseCase useCase = new SaveUserUseCase(new SaveUserRepository(userRepository));
                useCase.execute(saveUserInput);
                return useCase.getSaveUserOutput();
                                
        }

}
