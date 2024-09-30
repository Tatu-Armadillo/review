package com.fiap.restaurant.review.application.controllers.restaurant;

import com.fiap.restaurant.review.application.controllers.mock.RestaurantModelTestData;
import com.fiap.restaurant.review.application.records.restaurant.FilterRestaurantRecord;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FilterRestaurantsControllerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FilterRestaurantsController filterRestaurantsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filterRestaurantsController = new FilterRestaurantsController(restaurantRepository);
    }

    @Test
    void filterResturants() {
        when(restaurantRepository.findAll(any(Specification.class)))
                .thenReturn(List.of(RestaurantModelTestData.createRestaurant()));
        var response = filterRestaurantsController.FilterResturants(new FilterRestaurantRecord("", "", " "));
        assertNotNull(response);
    }
}