package com.fiap.restaurant.review.domain.gateway.user;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;

public interface GetUserByCpfInterface {
    UserEntity getUserByCpf(String cpf);
}
