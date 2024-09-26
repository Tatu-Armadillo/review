package com.fiap.restaurant.review.application.controllers.table;

import com.fiap.restaurant.review.application.controllers.mock.RestaurantModelTestData;
import com.fiap.restaurant.review.application.controllers.mock.TableModelTestData;
import com.fiap.restaurant.review.application.records.booking.SimpleTableRecord;
import com.fiap.restaurant.review.infra.models.TableModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaveTableControllerTest {

    @Mock
    private TableRepository tableRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private SaveTableController saveTableController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveTableController = new SaveTableController(tableRepository,  restaurantRepository);
    }

    @Test
    void save() {
        when(restaurantRepository.findResturantByCnpj(anyString())).thenReturn(Optional.of(RestaurantModelTestData.createRestaurant()));
        when(tableRepository.save(any(TableModel.class))).thenReturn(TableModelTestData.createTable());

        var res = this.saveTableController.save(new SimpleTableRecord(1, ""));

        assertNotNull(res);
        verify(tableRepository).save(any(TableModel.class));
    }
}