package com.fiap.restaurant.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
