package com.fiap.restaurant.review.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.AddressModel;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    
}
