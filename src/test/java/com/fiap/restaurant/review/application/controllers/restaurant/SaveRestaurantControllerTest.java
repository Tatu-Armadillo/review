package com.fiap.restaurant.review.application.controllers.restaurant;

import com.fiap.restaurant.review.application.records.restaurant.AddressRecord;
import com.fiap.restaurant.review.application.records.restaurant.RestaurantRecord;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SaveRestaurantControllerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private SaveRestaurantController saveRestaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveRestaurantController = new SaveRestaurantController(restaurantRepository, addressRepository);
    }

    @Test
    void save() {

        final var response = saveRestaurantController
                .save(new RestaurantRecord("", "", "", "", LocalTime.now(), LocalTime.now(),
                        true, 0,
                        new AddressRecord("", "", "", "", "", "", 0.0, 0.0)));

        assertNotNull(response);
    }
}