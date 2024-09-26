package com.fiap.restaurant.review.application.controllers.table;

import com.fiap.restaurant.review.application.controllers.mock.TableModelTestData;
import com.fiap.restaurant.review.application.controllers.restaurant.FindAllResturantController;
import com.fiap.restaurant.review.infra.models.TableModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class FindAllTablesByRestaurantControllerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private FindAllResturantController findAllResturantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findAllResturantController = new FindAllResturantController(restaurantRepository);
    }

    @Test
    void findAllTablesByRestaurant() {

        List<TableModel> tableModels = List.of(TableModelTestData.createTable());

        var response = when(tableRepository.findAllTablesByResturant(anyString())).thenReturn(tableModels);

        assertNotNull(response);
    }
}