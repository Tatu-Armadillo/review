package com.fiap.restaurant.review.domain.gateway.user;

import com.fiap.restaurant.review.domain.entities.UserEntity;

public interface SaveUserInterface {
    UserEntity saveUser(UserEntity userEntity);
}
