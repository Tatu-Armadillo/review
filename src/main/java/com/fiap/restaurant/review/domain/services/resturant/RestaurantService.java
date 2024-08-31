package com.fiap.restaurant.review.domain.services.resturant;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.domain.services.address.SaveAddressService;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final SaveAddressService addressService;

    @Autowired
    public RestaurantService(final RestaurantRepository restaurantRepository, final SaveAddressService addressService) {
        this.restaurantRepository = restaurantRepository;
        this.addressService = addressService;
    }

    public RestaurantModel findByCnpj(final String cnpj) {
        return this.restaurantRepository.findResturantByCnpj(cnpj)
                .orElseThrow(() -> new NotFoundException("m=findByCnpj Not Found Resturante with CNPJ = " + cnpj));
    }

    public Page<RestaurantModel> findAllAndPageableByFilter(final Pageable pageable, final String filter) {
        return this.restaurantRepository.findAllResturantsByName(pageable, filter);
    }

    public RestaurantModel save(final RestaurantModel restaurant) {
        final var address = this.addressService.save(restaurant.getAddress());
        restaurant.setAddress(address);
        restaurant.setTotalGrade(0);

        if (restaurant.getAlwaysOpen() != null && restaurant.getAlwaysOpen()) {
            restaurant.setOpenHour(LocalTime.of(0, 0, 0));
            restaurant.setCloseHour(LocalTime.of(23, 59, 59));
        }

        return this.restaurantRepository.save(restaurant);
    }

}
