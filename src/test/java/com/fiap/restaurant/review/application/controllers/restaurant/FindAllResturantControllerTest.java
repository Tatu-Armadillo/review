package com.fiap.restaurant.review.application.controllers.restaurant;

import com.fiap.restaurant.review.application.controllers.mock.RestaurantModelTestData;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FindAllResturantControllerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FindAllResturantController findAllResturantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findAllResturantController = new FindAllResturantController(restaurantRepository);
    }

    @Test
    void showAllResturants() throws Exception {
        List<RestaurantModel> restaurantList = List.of(RestaurantModelTestData.createRestaurant());

        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        final var response = findAllResturantController.showAllResturants();
        assertNotNull(response);

    }
}