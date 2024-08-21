package com.fiap.restaurant.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.model.Address;
import com.fiap.restaurant.review.repository.AddressRepository;

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
