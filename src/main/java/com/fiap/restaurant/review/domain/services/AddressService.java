package com.fiap.restaurant.review.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.infra.models.Address;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(final Address entity) {
        return this.addressRepository.save(entity);
    }

}
