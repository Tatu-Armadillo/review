package com.fiap.restaurant.review.application.controllers.table;

import com.fiap.restaurant.review.infra.repositories.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FindAllTablesByRestaurantControllerTest {

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private FindAllTablesByRestaurantController findAllTablesByRestaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findAllTablesByRestaurantController = new FindAllTablesByRestaurantController(tableRepository);
    }

    @Test
    void findAllTablesByRestaurant() {

        final var response = findAllTablesByRestaurantController.findAllTablesByRestaurant("");

        assertNotNull(response);
    }
}