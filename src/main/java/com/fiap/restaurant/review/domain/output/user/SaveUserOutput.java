package com.fiap.restaurant.review.domain.output.user;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class SaveUserOutput implements OutputInterface{

    private UserEntity userEntity;
    private OutputStatus outputStatus;

    public SaveUserOutput(UserEntity userEntity, OutputStatus outputStatus) {
        this.userEntity = userEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.userEntity;
    }

}
