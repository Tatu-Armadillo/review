package com.fiap.restaurant.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.model.Restaurant;
import com.fiap.restaurant.review.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressService addressService;

    @Autowired
    public RestaurantService(final RestaurantRepository restaurantRepository, final AddressService addressService) {
        this.restaurantRepository = restaurantRepository;
        this.addressService = addressService;
    }

    public Restaurant save(final Restaurant restaurant) {
        final var address = this.addressService.save(restaurant.getAddress());
        restaurant.setAddress(address);
        restaurant.setTotalGrade(0);
        return this.restaurantRepository.save(restaurant);
    }

}
