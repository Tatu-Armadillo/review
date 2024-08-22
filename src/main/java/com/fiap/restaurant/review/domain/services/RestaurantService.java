package com.fiap.restaurant.review.domain.services;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.infra.models.Restaurant;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressService addressService;

    @Autowired
    public RestaurantService(final RestaurantRepository restaurantRepository, final AddressService addressService) {
        this.restaurantRepository = restaurantRepository;
        this.addressService = addressService;
    }

    public Page<Restaurant> showResturants(final Pageable pageable, final String name) {
        return this.restaurantRepository.findAllResturantsByName(pageable, name);
    }

    public Restaurant save(final Restaurant restaurant) {
        final var address = this.addressService.save(restaurant.getAddress());
        restaurant.setAddress(address);
        restaurant.setTotalGrade(0);

        if (restaurant.getAlwaysOpen() != null && restaurant.getAlwaysOpen()) {
            restaurant.setOpenHour(LocalTime.of(00, 00, 00));
            restaurant.setCloseHour(LocalTime.of(23, 59, 59));
        }

        return this.restaurantRepository.save(restaurant);
    }

}
