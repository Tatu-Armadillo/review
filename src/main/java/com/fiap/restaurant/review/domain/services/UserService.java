package com.fiap.restaurant.review.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findUserByCpf(final String cpf) {
        return this.userRepository.findUserByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("m=findUserByCpf Not Found User with cpf = " + cpf));
    }

    public UserModel save(final UserModel entity) {
        return this.userRepository.findUserByCpf(entity.getCpf())
                .orElseGet(() -> this.userRepository.save(entity));

    }

}
