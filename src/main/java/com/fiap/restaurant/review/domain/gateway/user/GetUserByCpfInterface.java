package com.fiap.restaurant.review.domain.gateway.user;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.exceptions.NotFoundException;

public interface GetUserByCpfInterface {
    UserEntity getUserByCpf(String cpf) throws NotFoundException;
}
