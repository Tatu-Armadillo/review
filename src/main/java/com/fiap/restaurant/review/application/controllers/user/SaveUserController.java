package com.fiap.restaurant.review.application.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.restaurant.review.application.records.user.UserRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.user.SaveUserInput;
import com.fiap.restaurant.review.domain.services.UserService;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user/save")
@Tag(name = "Users", description = "Endpoints for Managing users")
public class SaveUserController {

        private final UserService userService;

        @Autowired
        public SaveUserController(final UserService userService) {
                this.userService = userService;
        }

        @PostMapping
        @Operation(tags = {"User"})
        public ResponseEntity<ResponseBase<UserRecord>> save(@RequestBody final UserRecord userRecord) {
                // getOutputInterface()
                // OutputInterface outputInterface = getOutputInterface(userRecord)
                // SaveUserPresenter(SaveUserOutput)
                final var response = this.userService.save(UserRecord.toEntity(userRecord));
                final var base = ResponseBase.of(UserRecord.toRecord(response));
                return ResponseEntity.ok(base);
        }

        // private OutputInterface getOutputInterface(UserRecord userRecord){
        //         SaveUserInput saveUserInput = new SaveUserInput(
        //                 userRecord.cpf(),
        //                 userRecord.phone(), 
        //                 userRecord.username(),
        //                 userRecord.fullname(),
        //                 userRecord.password()
        //                 );
                                
        // }

}
