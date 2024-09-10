package com.fiap.restaurant.review.infra.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findUserByCpf(String cpf);

}
