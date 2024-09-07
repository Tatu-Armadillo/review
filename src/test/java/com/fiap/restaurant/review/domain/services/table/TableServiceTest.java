package com.fiap.restaurant.review.domain.services.table;

import com.fiap.restaurant.review.domain.services.resturant.RestaurantService;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.models.TableModel;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private TableService tableService;

    private TableModel tableModel;
    private RestaurantModel restaurantModel;

    @BeforeEach
    void setUp() {
        restaurantModel = new RestaurantModel();
        restaurantModel.setCnpj("12345678000100");

        tableModel = new TableModel();
        tableModel.setRestaurant(restaurantModel);
    }

    @Test
    void testFindAllTablesByResturant() {
        Pageable pageable = PageRequest.of(0, 10);
        String cnpj = "12345678000100";
        List<TableModel> tableList = List.of(tableModel);
        Page<TableModel> tablePage = new PageImpl<>(tableList);

        when(tableRepository.findAllTablesByResturant(any(Pageable.class), anyString()))
                .thenReturn(tablePage);

        Page<TableModel> result = tableService.findAllTablesByResturant(pageable, cnpj);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(tableModel, result.getContent().get(0));
        verify(tableRepository, times(1)).findAllTablesByResturant(pageable, cnpj);
    }

    @Test
    void testSaveTable() {
        when(restaurantService.findByCnpj(anyString())).thenReturn(restaurantModel);
        when(tableRepository.save(any(TableModel.class))).thenReturn(tableModel);

        TableModel savedTable = tableService.save(tableModel);

        assertNotNull(savedTable);
        assertEquals(restaurantModel, savedTable.getRestaurant());
        assertFalse(savedTable.getAvailable());
        verify(restaurantService, times(1)).findByCnpj(restaurantModel.getCnpj());
        verify(tableRepository, times(1)).save(tableModel);
    }
}
