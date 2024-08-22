package com.fiap.restaurant.review.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.infra.models.User;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByCpf(final String cpf) {
        return this.userRepository.findUserByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("m=findUserByCpf Not Found User with cpf = " + cpf));
    }

    public User save(final User entity) {
        return this.userRepository.findUserByCpf(entity.getCpf())
                .orElseGet(() -> this.userRepository.save(entity));

    }

}
